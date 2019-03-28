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
import sample.services.auth.Auth;

import java.io.IOException;
import java.net.URL;
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
        sign_in_button.setOnAction(event -> handle(event));
    }


    private void handle(ActionEvent event){

        String login =  this.sign_in_login.getText().trim();
        String password =  this.sign_in_password.getText().trim();

        if(!login.equals("") && !password.equals("")){
            System.out.println("Press login button. Login: " + login + ". Password: " + password);

            if(Auth.login(login,password)){
                sign_in_button.getScene().getWindow().hide();
                this.showNextWindow();
            }

        }else {
            System.out.println("Fill all fields pls!");
        }

    }

    private void showNextWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/views/competitionList.fxml"));
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
