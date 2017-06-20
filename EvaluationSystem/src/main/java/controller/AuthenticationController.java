package controller;

import exception.*;
import model.User;
import org.orm.PersistentException;
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

    UserService userService;
    JwtService jwtService;

    public AuthenticationController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody LoginWrapper userLogin){
        try {
            User user = userService.login(userLogin.getEmail(), userLogin.getPassword());
            String token = jwtService.createToken(user.getID());
            return new ResponseEntity<Object>(new LoginResponseWrapper(token, user), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (UnconfirmedEmailException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(UNCONFIRMED_EMAIL), UNAUTHORIZED);
        } catch (InvalidUserException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_AUTHENTICATION), UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signup(@RequestBody SignupWrapper userSignup){
        User userDetails = new User();
        userDetails.setEmail(userSignup.getEmail());
        userDetails.setPassword(userSignup.getPassword());
        userDetails.setFirstName(userSignup.getFirstName());
        userDetails.setLastName(userSignup.getLastName());

        try {
            User user = userService.signup(userDetails, userSignup.getType());
            return new ResponseEntity<Object>(new UserWrapper(user), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (MissingInformationException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(MISSING_INFORMATION), NOT_ACCEPTABLE);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(EMAIL_IN_USE), NOT_ACCEPTABLE);
        } catch (InvalidUserTypeException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_USER_TYPE), NOT_ACCEPTABLE);
        }
    }
}
