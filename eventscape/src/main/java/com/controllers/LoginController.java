package com.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.model.*;

public class LoginController {

    @FXML
    private Button Login;

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
