package com.model;

public class Driver {
    public static void main(String[] args) {
        UserFacade facade = new UserFacade();

        // Try creating a user
        facade.createAccount("brionna", "password123");

        // Try logging in
        boolean success = facade.login("brionna", "password123");
        System.out.println("Login successful: " + success);
    }
}