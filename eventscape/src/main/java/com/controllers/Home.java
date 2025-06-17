package com.controllers;

import com.model.Facade;
import com.model.User;
import com.model.Event;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class Home {

    @FXML
    private Label welcomeLabel;

    @FXML
    private VBox eventListVBox;

    @FXML
    private Button secondaryButton;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        welcomeLabel.setText("Welcome, " + user.getFirstName() + "!");

        // Load and display events
        Facade facade = Facade.getInstance();
        List<Event> events = facade.getAllEvents(); // make sure this method exists

        for (Event e : events) {
            Label eventLabel = new Label(e.getName() + " - " + e.getDate());
            eventListVBox.getChildren().add(eventLabel);
        }
    }

    @FXML
    private void switchToPrimary(ActionEvent event) {
        System.out.println("Back to primary view logic goes here...");
        // Add logic to switch to login or another scene if needed
    }
}


