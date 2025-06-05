package com.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UI {
    private final Scanner scanner = new Scanner(System.in);
    private final Facade facade = Facade.getInstance();

    public void start() {
        System.out.println("Welcome to the Event Management System");
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    createAccount();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = facade.login(username, password); // Add this method in Facade if not present

        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getFirstName() + ".");
            // TODO:more functionality for users l8er
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void createAccount() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Birth date (yyyy-MM-dd): ");
        String birthDateStr = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        Date birthDate;
        try {
            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }

        User newUser = new User(username, firstName, lastName, email, phone, birthDate, password, false, 0, false);

        if (facade.addUser(newUser)) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Username already taken.");
        }
    }
}
