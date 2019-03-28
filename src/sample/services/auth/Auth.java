package sample.services.auth;

import sample.models.UserModel;

public class Auth {

    public static boolean login(String name, String password){
        return UserModel.find(name, password) != null;
    }

}
