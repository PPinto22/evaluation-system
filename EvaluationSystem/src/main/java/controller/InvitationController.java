package controller;

import dao.ClassesPersistentManager;
import exception.InvalidClaimsException;
import exception.NonExistentEntityException;
import exception.UnconfirmedRegistrationException;
import io.jsonwebtoken.Claims;
import model.Group;
import model.GroupInvitation;
import model.User;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.NotificationService;
import wrapper.ErrorWrapper;
import wrapper.GroupClassWrapper;

import javax.servlet.http.HttpServletRequest;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/api/invitations")
public class InvitationController {

    private JwtService jwtService;
    private NotificationService notificationService;

    public InvitationController(JwtService jwtService, NotificationService notificationService) {
        this.jwtService = jwtService;
        this.notificationService = notificationService;
    }

    @RequestMapping(value = "/{id:[\\d]+}/accept", method = POST)
    public ResponseEntity<Object> accept(@PathVariable int id, HttpServletRequest request) throws PersistentException {
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            GroupInvitation groupInvitation = (GroupInvitation) notificationService.getNotificationByID(session, id);
            if(user.getID() != groupInvitation.get_user().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            Group group = notificationService.acceptInvitation(session, groupInvitation);
            return new ResponseEntity<Object>(new GroupClassWrapper(group), OK);
        } catch (PersistentException | ClassCastException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_INVITATION), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_AUTHENTICATION), UNAUTHORIZED);
        } catch (UnconfirmedRegistrationException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(UNCONFIRMED_EMAIL), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{id:[\\d]+}/decline", method = POST)
    public ResponseEntity<Object> decline(@PathVariable int id, HttpServletRequest request) throws PersistentException {
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            GroupInvitation groupInvitation = (GroupInvitation) notificationService.getNotificationByID(session, id);
            if(user.getID() != groupInvitation.get_user().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            notificationService.declineInvitation(groupInvitation);
            return new ResponseEntity<Object>(new Object(), OK);
        } catch (PersistentException | ClassCastException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_INVITATION), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_AUTHENTICATION), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }
}
