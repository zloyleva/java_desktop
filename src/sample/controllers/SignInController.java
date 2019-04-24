package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.java_beans.User;
import sample.services.auth.Auth;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField sign_in_password;

    @FXML
    private Button sign_in_button;

    @FXML
    private TextField sign_in_login;

    @FXML
    void initialize() {
        sign_in_button.setOnAction(event -> {
            try {
                handle(event);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    private void handle(ActionEvent event) throws SQLException {

        String login =  this.sign_in_login.getText().trim();
        String password =  this.sign_in_password.getText().trim();

        if(!login.equals("") && !password.equals("")){
            System.out.println("Press login button. Login: " + login + ". Password: " + password);

            Auth auth = new Auth();

            if(auth.login(login,password)){
                sign_in_button.getScene().getWindow().hide();
                this.showNextWindow(auth.getUser());
            }

        }else {
            System.out.println("Fill all fields pls!");
        }

    }

    private void showNextWindow(User user){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/views/user.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("User's area");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
