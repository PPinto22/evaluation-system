package wrapper;

import model.persistent.User;

public class LoginResponseWrapper {

    private String token;
    private UserWrapper user;

    public LoginResponseWrapper(){}

    public LoginResponseWrapper(String token, User user) {
        this.user = new UserWrapper(user);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserWrapper getUser() {
        return user;
    }

    public void setUser(UserWrapper user) {
        this.user = user;
    }
}
