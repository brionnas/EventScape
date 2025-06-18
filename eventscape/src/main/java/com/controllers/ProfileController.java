package com.controllers;

import com.model.Facade;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

import com.event.App;

public class ProfileController {

    @FXML
    private Label profileNameLabel;

    @FXML
    private Label profileEmailLabel;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button saveProfileButton;

    @FXML
    private Button cancelEditButton;

    @FXML
    private Button signOutButton;

    @FXML
    private VBox profileInfoVBox;

    private User currentUser;
    private boolean isEditMode = false;

    public void setUser(User user) {
        this.currentUser = user;
        updateProfileDisplay();
    }

    private void updateProfileDisplay() {
        if (currentUser != null) {
            profileNameLabel.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
            profileEmailLabel.setText(currentUser.getEmail());
            
            firstNameField.setText(currentUser.getFirstName());
            lastNameField.setText(currentUser.getLastName());
            emailField.setText(currentUser.getEmail());
            phoneField.setText(currentUser.getPhoneNumber() != null ? currentUser.getPhoneNumber() : "");
            
            setEditMode(false);
        }
    }

    private void setEditMode(boolean editMode) {
        this.isEditMode = editMode;
        
        firstNameField.setEditable(editMode);
        lastNameField.setEditable(editMode);
        emailField.setEditable(editMode);
        phoneField.setEditable(editMode);
        
        editProfileButton.setVisible(!editMode);
        saveProfileButton.setVisible(editMode);
        cancelEditButton.setVisible(editMode);
        
        if (editMode) {
            firstNameField.getStyleClass().add("editable");
            lastNameField.getStyleClass().add("editable");
            emailField.getStyleClass().add("editable");
            phoneField.getStyleClass().add("editable");
        } else {
            firstNameField.getStyleClass().remove("editable");
            lastNameField.getStyleClass().remove("editable");
            emailField.getStyleClass().remove("editable");
            phoneField.getStyleClass().remove("editable");
        }
    }

    @FXML
    private void handleEditProfile(ActionEvent event) {
        setEditMode(true);
    }

    @FXML
    private void handleSaveProfile(ActionEvent event) {
        // In a real app, you would save to database
        if (currentUser != null) {
          //  currentUser.setFirstName(firstNameField.getText());
           // currentUser.setLastName(lastNameField.getText());
           // currentUser.setEmail(emailField.getText());
           // currentUser.setPhoneNumber(phoneField.getText());
            
            // Update the display
            profileNameLabel.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
            profileEmailLabel.setText(currentUser.getEmail());
        }
        
        setEditMode(false);
        System.out.println("Profile saved successfully!");
    }

    @FXML
    private void handleCancelEdit(MouseEvent event) {
        // Reset fields to original values
        updateProfileDisplay();
    }

    @FXML
    private void handleSignOut(MouseEvent event) {
        try {
            App.setRoot("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToHome(MouseEvent event) {
        try {
            App.setRoot("home");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToTickets(MouseEvent event) {
        try {
            App.setRoot("tickets");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
