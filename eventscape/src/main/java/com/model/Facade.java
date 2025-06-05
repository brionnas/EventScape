package com.model;


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

    public User findUser(String username) {
        return userList.getUserByUsername(username);
    }

    public boolean addUser(String username) {
        User user = findUser(username);
        if (user != null) {
            userList.removeUser(user);
            return true;
        }
        return false;
    }
}
