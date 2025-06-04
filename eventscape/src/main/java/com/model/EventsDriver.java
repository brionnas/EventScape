package com.model;

public class EventsDriver {
    public static void main(String[] args) {
        String userFilePath = "eventscape/src/main/java/com/model/User.java"; // Adjust to your project structure
        List<User> users = DataLoader.loadUsers(userFilePath);

        for (User user : users) {
            System.out.println("User: " + user.getUserName() + " | Email: " + user.getEmail());
        }
    }
}
