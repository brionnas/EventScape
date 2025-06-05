package com.model;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UI {
    private Scanner scanner = new Scanner(System.in);
    private Facade facade = Facade.getInstance();  // Assuming singleton facade for users

    public void start() {
        System.out.println("Welcome to the Event Management System");
        boolean running = true;
        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    createAccount();
                    break;
                case "3":
                    System.out.println("Thank you for using the system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = facade.login(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getFirstName() + ".");
            // You can extend here for user menu or event browsing
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void createAccount() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter birth date (yyyy-MM-dd): ");
        String birthDateStr = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Date birthDate;
        try {
            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please try again.");
            return;
        }

        User newUser = new User(username, firstName, lastName, email, phone, birthDate, password, false, 0, false);

        if (facade.addUser(newUser)) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Username already exists or error creating account.");
        }
    }
}
