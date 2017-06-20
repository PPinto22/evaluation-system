package controller;

import exception.*;
import model.User;
import org.orm.PersistentException;
import org.springframework.http.ResponseEntity;
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

    /**
     *
     * @param userLogin
     * {
     *  "email": "something@something",
     *  "password": "some_password"
     * }
     * @return
     * HttpStatus:
     * - OK
     * - INTERNAL_SERVER_ERROR
     * - UNAUTHORIZED (Invalid authentication, or Unconfirmed email)
     * response format:
     * {
     *  "token": "header.body.signature",
     *  "user":{
     *      "email": "something@something",
     *      "firstName": "Foo",
     *      "lastName": "Bar",
     *      "type": "student" or "teacher",
     *      "id": id (integer)
     *  }
     * }
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(LoginWrapper userLogin){
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


    /**
     *
     * @param userSignup
     * {
     *  "email": "something@something",
     *  "password": "some_password",
     *  "firstName": "Foo",
     *  "lastName": "Bar",
     *  "type": "student" or "teacher"
     * }
     * @return
     * HttpStatus:
     * - OK
     * - INTERNAL_SERVER_ERROR
     * - NOT_ACCEPTABLE (Missing information, Email already in use, or Invalid user type)
     * Response Format:
     * {
     *  "email": "something@something",
     *  "firstName": "Foo",
     *  "lastName": "Bar",
     *  "type": "student" or "teacher",
     *  "id": id (integer)
     * }
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signup(SignupWrapper userSignup){
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
