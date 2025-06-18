/*package com.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.model.Event;
import com.model.Facade;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddEvent implements javafx.fxml.Initializable{
    
    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField eventNameField;

    @FXML
    private TextField eventDateField;

    @FXML
    private TextField eventLocationField;

    @FXML
    private Button addEventButton;

    @FXML
    private VBox eventListVBox;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        welcomeLabel.setText("Welcome, " + user.getFirstName() + "!");
        loadEvents();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization logic if needed
    }

    @FXML
    private void addEvent(ActionEvent event) {
        String name = eventNameField.getText();
        String date = eventDateField.getText();
        String location = eventLocationField.getText();

        if (name.isEmpty() || date.isEmpty() || location.isEmpty()) {
            // Handle empty fields (e.g., show an error message)
            return;
        }

        Facade facade = Facade.getInstance();
        // Call addEvent with the required String arguments
        facade.addEvent(name, date, location, currentUser.getFirstName(), "");

        loadEvents();
        clearFields();
    }

    private void loadEvents() {
        Facade facade = Facade.getInstance();
        List<Event> events = facade.getAllEvents();

        eventListVBox.getChildren().clear();

        for (Event event : events) {
            VBox eventCard = createEventCard(event);
            eventListVBox.getChildren().add(eventCard);
        }
    }

    private VBox createEventCard(Event event) {
        VBox card = new VBox();
        card.setStyle("-fx-padding: 10; -fx-border-color: #ccc; -fx-border-width: 1;");

        Label nameLabel = new Label("Name: " + event.getName());
        Label dateLabel = new Label("Date: " + event.getDate());
        Label locationLabel = new Label("Location: " + event.getLongitude() + event.getLatitude());

        card.getChildren().addAll(nameLabel, dateLabel, locationLabel);
        
        return card;
    }

    private void clearFields() {
        eventNameField.clear();
        eventDateField.clear();
        eventLocationField.clear();
    }
}
*/