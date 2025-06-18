package com.controllers;

import com.model.Facade;
import com.model.User;
import com.model.Event;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.control.ScrollPane;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.event.App;

public class Home implements javafx.fxml.Initializable {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField searchField;

    @FXML
    private VBox eventListVBox;

    @FXML
    private Button secondaryButton;

    @FXML
    private HBox categoriesHBox;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        welcomeLabel.setText("Welcome, " + user.getFirstName() + "!");
        loadEvents();
        setupCategories();
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
        VBox eventCard = new VBox();
        eventCard.getStyleClass().add("event-card");

        
        VBox eventImage = new VBox();
        eventImage.getStyleClass().add("event-image");
        eventImage.setPrefHeight(100);
        
        Label eventEmoji = new Label("🎵"); 
        eventEmoji.getStyleClass().add("event-emoji");
        eventImage.getChildren().add(eventEmoji);

        // Event details
        VBox eventDetails = new VBox();
        eventDetails.getStyleClass().add("event-details");

        Label eventTitle = new Label(event.getName());
        eventTitle.getStyleClass().add("event-title");

        HBox eventMeta = new HBox();
        eventMeta.getStyleClass().add("event-meta");

        Label eventCategory = new Label("Music"); 
        eventCategory.getStyleClass().add("event-category");

        Label eventDate = new Label(event.getDate().toString());
        eventDate.getStyleClass().add("event-date");

        eventMeta.getChildren().addAll(eventCategory, eventDate);

        HBox eventCapacity = new HBox();
        eventCapacity.getStyleClass().add("event-capacity");

        Label capacityLabel = new Label("Available: ");
        Label capacityCount = new Label("50 spots");
        capacityCount.getStyleClass().add("capacity-available");

        eventCapacity.getChildren().addAll(capacityLabel, capacityCount);

        Button viewDetailsBtn = new Button("View Details");
        viewDetailsBtn.getStyleClass().add("btn-secondary");
        viewDetailsBtn.setOnAction(this::handleViewDetails);

        eventDetails.getChildren().addAll(eventTitle, eventMeta, eventCapacity, viewDetailsBtn);
        eventCard.getChildren().addAll(eventImage, eventDetails);

        return eventCard;
    }

    private void setupCategories() {
        if (categoriesHBox != null) {
            String[] categories = {"All", "Music", "Sports", "Art", "Food", "Tech"};
            
            for (String category : categories) {
                Button categoryBtn = new Button(category);
                categoryBtn.getStyleClass().add("category-chip");
                if (category.equals("All")) {
                    categoryBtn.getStyleClass().add("active");
                }
                categoryBtn.setOnAction(this::handleCategorySelection);
                categoriesHBox.getChildren().add(categoryBtn);
            }
        }
    }

    @FXML
    private void handleCategorySelection(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        
        
        if (categoriesHBox != null) {
            categoriesHBox.getChildren().forEach(node -> {
                if (node instanceof Button) {
                    node.getStyleClass().remove("active");
                }
            });
        }
        
        
        if (!clickedButton.getStyleClass().contains("active")) {
            clickedButton.getStyleClass().add("active");
        }
        
        
        System.out.println("Category selected: " + clickedButton.getText());
    }

    @FXML
    private void handleViewDetails(ActionEvent event) {
        System.out.println("View Details clicked");
       
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        String searchTerm = searchField.getText();
        System.out.println("Searching for: " + searchTerm);
        
    }

    @FXML
    private void switchToPrimary(ActionEvent event) {
        try {
            App.setRoot("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToTickets(MouseEvent event) {
         try {
            App.setRoot("tickets");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToProfile(MouseEvent event) {
        try {
            App.setRoot("profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setUser(Facade.getInstance().getCurrentUser());
    }
}


