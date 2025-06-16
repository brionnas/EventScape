package com.controllers;

import com.model.Facade;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button Login;

    @FXML
    private Label lblErrorMessage;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void login(ActionEvent event) {
         String userName = txtUserName.getText();
        String password = txtPassword.getText(); 

        Facade facade = Facade.getInstance(); 

        User user = facade.login(userName, password);
        if( user == null) {
            System.out.println("error");
            return;
        }

        
        System.out.println("Welcome " + user.getFirstName());

    }

}
