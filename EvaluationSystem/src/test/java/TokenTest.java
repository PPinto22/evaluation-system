import exception.InvalidClaimsException;
import io.jsonwebtoken.Claims;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import security.JwtService;
import service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class TokenTest{

    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;

    @Test
    public void testTokenCreation() throws PersistentException, InvalidClaimsException {
        User[] users = userService.getStudents();
        if(users.length > 0) {
            int id = users[0].getID();

            String token = jwtService.createToken(id);
            Claims claims = jwtService.getClaims(token);
            User user = jwtService.getUser(claims);

            Assert.assertNotNull(token);
            Assert.assertTrue(claims.getSubject().equals(String.valueOf(id)));
            Assert.assertTrue(claims.getSubject().equals(String.valueOf(user.getID())));
        }
    }
}
