package com.model;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static UserList instance;
    private List<User> users;

    private UserList() {
        users = DataLoader.getUsers(); // Load users from file
        if (users == null) {
            users = new ArrayList<>(); // Initialize with an empty list if loading fails
        }
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    // ... other methods ...

public User getUserByUsername(String username) {
    for (User user : getUsers()) {
        if (user.getUserName().equals(username)) {
            return user;
        }
    }
    return null;
}

    public void removeUser(User user) {
        users.remove(user);
    }
}
