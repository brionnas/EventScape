package com.controllers;

import java.io.IOException;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/event/home.fxml"));
        Parent root = loader.load();

        // Access Home controller and pass the User
        Home homeController = loader.getController();
        homeController.setUser(user);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
