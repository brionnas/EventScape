package com.model;

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

    public User login(String username, String password) {
        user = userList.getUserByUsername(username);
        if (user != null && user.getPasswordHash().equals(password)) {
            return user;
        }
        return null;
    }

    public void logout() {
        userList.save();
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
