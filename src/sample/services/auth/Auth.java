package sample.services.auth;

import sample.java_beans.User;
import sample.models.UserModel;

import java.sql.SQLException;

public class Auth {

    public static User user;

    public User getUser() {
        return user;
    }

    public boolean login(String name, String password) throws SQLException {
        user = UserModel.find(name, password);
        return user != null;
    }
}
