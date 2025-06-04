package com.model;

import java.util.List;

public class Facade {
    private static Facade instance;
    private UserList userList;

    private Facade() {
        userList = UserList.getInstance();
    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public List<User> getAllUsers() {
        return userList.getUsers();
    }

    public boolean addUser(User user) {
        if (userList.getUserByUsername(user.getUserName()) == null) {
            userList.addUser(user);
            return true;
        }
        return false; // user already exists
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
}
