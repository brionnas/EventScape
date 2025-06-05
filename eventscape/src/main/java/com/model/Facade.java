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

    public User findUser(User newUser) {
        return userList.getUserByUsername(newUser);
    }

    public boolean addUser(User newUser) {
        User user = findUser(newUser);
        if (user != null) {
            userList.removeUser(user);
            return true;
        }
        return false;
    }

    public User login(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}
