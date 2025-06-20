package com.controllers;

import com.model.Facade;
import com.model.User;
import com.model.Event;
import com.model.Ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;

import com.event.App;

public class TicketsController implements Initializable {

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
        // Always get the latest user data from Facade
        User latestUser = Facade.getInstance().getCurrentUser();
        if (latestUser != null) {
            this.currentUser = latestUser;
        }
        
        ticketsListVBox.getChildren().clear();
        
        // Get the actual tickets from the current user
        ArrayList<Ticket> userTickets = currentUser != null ? currentUser.getTickets() : null;
        
        System.out.println("Loading tickets for user: " + (currentUser != null ? currentUser.getFirstName() : "null"));
        System.out.println("Number of tickets: " + (userTickets != null ? userTickets.size() : 0));
        
        if (userTickets == null || userTickets.isEmpty()) {
            Label noTicketsLabel = new Label("No tickets purchased yet");
            noTicketsLabel.getStyleClass().add("no-tickets-label");
            ticketsListVBox.getChildren().add(noTicketsLabel);
            return;
        }
        
        // Display each ticket the user has purchased
        for (Ticket ticket : userTickets) {
            VBox ticketItem = createTicketItemFromTicket(ticket);
            ticketsListVBox.getChildren().add(ticketItem);
        }
    }

    // Updated method to work with Ticket objects
    private VBox createTicketItemFromTicket(Ticket ticket) {
        VBox ticketItem = new VBox();
        ticketItem.getStyleClass().add("ticket-item");
        
        Event event = ticket.getEvent();
        
        Label titleLabel = new Label(event.getName());
        titleLabel.getStyleClass().add("ticket-title");
        
        Label dateLabel = new Label("Date: " + event.getDate());
        
        Label statusLabel = new Label(ticket.getStatus());
        statusLabel.getStyleClass().add("ticket-status");
        
        // Handle potential null ticket ID
        String ticketIdString = ticket.getTicketId() != null ? 
            ticket.getTicketId().toString().substring(0, Math.min(8, ticket.getTicketId().toString().length())) : 
            "Unknown ID";
        
        Label ticketIdLabel = new Label("Ticket ID: #" + ticketIdString);
        
        ticketItem.getChildren().addAll(titleLabel, dateLabel, statusLabel, ticketIdLabel);
        
        return ticketItem;
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
    private void switchToProfile(MouseEvent event) {
        try {
            App.setRoot("profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set the current user when the controller initializes
        setUser(Facade.getInstance().getCurrentUser());
    }
    
    // Add this method to refresh tickets when the view becomes visible
    public void refreshTickets() {
        loadUserTickets();
    }
}