package controller;

import dao.ClassesPersistentManager;
import exception.*;
import model.User;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.UserService;
import wrapper.*;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private UserService userService;
    private JwtService jwtService;

    public AuthenticationController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody LoginWrapper userLogin) throws PersistentException{
        ResponseEntity<Object> resp = null;
        PersistentSession session =  null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = userService.login(session, userLogin.getEmail(), userLogin.getPassword());
            String token = jwtService.createToken(user.getID());
            return new ResponseEntity<Object>(new LoginResponseWrapper(token, user), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (UnconfirmedRegistrationException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(UNCONFIRMED_EMAIL), UNAUTHORIZED);
        } catch (InvalidAuthenticationException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_AUTHENTICATION), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signup(@RequestBody SignupWrapper userSignup) throws PersistentException{
        User userDetails = new User();
        userDetails.setEmail(userSignup.getEmail());
        userDetails.setPassword(userSignup.getPassword());
        userDetails.setFirstName(userSignup.getFirstName());
        userDetails.setLastName(userSignup.getLastName());

        ResponseEntity<Object> resp = null;
        PersistentSession session =  null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = userService.signup(session, userDetails, userSignup.getType(), true);
            return new ResponseEntity<Object>(new UserWrapper(user), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (MissingInformationException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(MISSING_INFORMATION), NOT_ACCEPTABLE);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(EMAIL_IN_USE), NOT_ACCEPTABLE);
        } catch (InvalidUserTypeException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_USER_TYPE), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }
}
