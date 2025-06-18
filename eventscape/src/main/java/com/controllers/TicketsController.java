package com.controllers;

import com.model.Facade;
import com.model.User;
import com.model.Event;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

import com.event.App;

public class TicketsController {

    @FXML
    private Label ticketsHeaderLabel;

    @FXML
    private VBox ticketsListVBox;

    @FXML
    private Button backToHomeButton;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        ticketsHeaderLabel.setText("My Tickets - " + user.getFirstName());
        loadUserTickets();
    }

    private void loadUserTickets() {
        
        Facade facade = Facade.getInstance();
        List<Event> events = facade.getAllEvents();
        
       
        ticketsListVBox.getChildren().clear();
        
        
        for (int i = 0; i < Math.min(events.size(), 3); i++) {
            Event event = events.get(i);
            VBox ticketItem = createTicketItem(event, i == 0 ? "Confirmed" : "Pending");
            ticketsListVBox.getChildren().add(ticketItem);
        }
    }

    private VBox createTicketItem(Event event, String status) {
        VBox ticketItem = new VBox();
        ticketItem.getStyleClass().add("ticket-item");
        
        Label titleLabel = new Label(event.getName());
        titleLabel.getStyleClass().add("ticket-title");
        
        Label dateLabel = new Label("Date: " + event.getDate());
        
        Label statusLabel = new Label(status);
        statusLabel.getStyleClass().add("ticket-status");
        if (status.equals("Pending")) {
            statusLabel.getStyleClass().add("pending");
        }
        
        Label ticketIdLabel = new Label("Ticket ID: #" + (int)(Math.random() * 10000));
        
        ticketItem.getChildren().addAll(titleLabel, dateLabel, statusLabel, ticketIdLabel);
        
        return ticketItem;
    }

    @FXML
    private void switchToHome(ActionEvent event) {
        try {
            App.setRoot("home");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToProfile(ActionEvent event) {
        try {
            App.setRoot("profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
