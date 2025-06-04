package com.model;

import java.util.List;

public class EventsDriver {
    public static void main(String[] args) {
        // Use the correct method and file path:
        List<User> users = DataLoader.getUsers();

        for (User user : users) {
            System.out.println("User: " + user.getUserName() + " | Email: " + user.getEmail());
        }
    }
}

