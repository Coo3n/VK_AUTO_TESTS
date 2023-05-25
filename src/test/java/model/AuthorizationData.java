package model;


public class AuthorizationData {
    private final String email;
    private final String password;

    private static AuthorizationData instance;

    private AuthorizationData() {
        email = "botS23AT24";
        password = "autotests2023";
    }

    public static AuthorizationData getInstance() {
        if (instance == null) {
            instance = new AuthorizationData();
        }
        return instance;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
