package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Facade {
    private static Facade instance;
    private final UserList userList;
    private final EventList eventList;
    private User user;

    private Facade() {
        userList = UserList.getInstance();
        eventList = EventList.getInstance();
    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    // --- User Methods ---
    public List<User> getAllUsers() {
        return userList.getUsers();
    }

    public boolean addUser(String userName, String firstName, String lastName, String email, String phoneNumber, Date birthDate, String passwordHash) {
        return userList.addUser(userName, firstName, lastName, email, phoneNumber, birthDate, passwordHash);
    }

    public User findUser(String username) {
        return userList.getUserByUsername(username);
    }

    public boolean removeUser(String username) {
        User user = findUser(username);
        if (user != null) {
            userList.removeUser(user);
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return user;
    }

    // Added this method for proper user state management
    public void setCurrentUser(User user) {
        this.user = user;
    }

    public User login(String username, String password) {
        user = userList.getUserByUsername(username);
        if (user != null && user.getPasswordHash().equals(password)) {
            // Initialize tickets list if null
            if (user.getTickets() == null) {
                user.setTickets(new ArrayList<>());
            }
            return user;
        }
        return null;
    }

    public void logout() {
        userList.save();
        user = null; // Clear current user on logout
    }

    // --- Event Methods ---
    public boolean addEvent(String name, String startDate, String endDate, String location, String description) {
        Event event = new Event(name, startDate, endDate, location, description);
        eventList.addEvent(event);
        return true;
    }

    public boolean removeEvent(String eventId) {
        try {
            java.util.UUID uuid = java.util.UUID.fromString(eventId);
            Event event = eventList.getEventById(uuid);
            if (event != null) {
                eventList.removeEvent(event);
                return true;
            }
        } catch (IllegalArgumentException e) {
            // Invalid UUID string
        }
        return false;
    }

    public List<Event> getAllEvents() {
        return eventList.getEvents();
    }

    public Event getEventById(String eventId) {
        try {
            java.util.UUID uuid = java.util.UUID.fromString(eventId);
            return eventList.getEventById(uuid);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // Fixed the addTicketToUser method
    public void addTicketToUser(User user, Ticket ticket) {
        if (user == null || ticket == null) {
            System.out.println("Error: User or Ticket is null");
            return;
        }
        
        if (user.getTickets() == null) {
            user.setTickets(new ArrayList<>());
        }
        user.getTickets().add(ticket);
        
        Event event = ticket.getEvent();
        if (event != null && event.getTicketsLeft() > 0) {
            // Fixed: Actually decrement the tickets left
            event.setTicketsLeft(event.getTicketsLeft() - 1);
            System.out.println("Ticket added. Remaining tickets for " + event.getName() + ": " + event.getTicketsLeft());
        } else if (event == null) {
            System.out.println("Error: Event in ticket is null");
        } else {
            System.out.println("Warning: No tickets left for event " + event.getName());
        }
    }

    // New method to purchase a ticket (combines ticket creation and adding to user)
    public boolean purchaseTicket(User user, Event event) {
        if (user == null || event == null) {
            System.out.println("Error: User or Event is null");
            return false;
        }
        
        if (event.getTicketsLeft() <= 0) {
            System.out.println("Error: No tickets available for " + event.getName());
            return false;
        }
        
        try {
            Ticket newTicket = new Ticket(event, user);
            addTicketToUser(user, newTicket);
            
            // Update the current user if this is the current user
            if (this.user != null && this.user.equals(user)) {
                this.user = user;
            }
            
            System.out.println("Successfully purchased ticket for " + event.getName());
            return true;
        } catch (Exception e) {
            System.out.println("Error purchasing ticket: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // --- Persistence ---
    public boolean loadData() {
        return userList.load() && eventList.load();
    }

    public boolean saveData() {
        userList.save();
        eventList.save();
        return true;
    }
}
