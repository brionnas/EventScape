package com.controllers;

import java.io.IOException;

import com.event.App;
import com.model.Facade;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

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

    if (user == null) {
        lblErrorMessage.setText("Invalid username or password.");
        return;
    }

    System.out.println("Welcome " + user.getFirstName());

    try {
       App.setRoot("home");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
