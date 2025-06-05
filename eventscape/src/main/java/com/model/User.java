package com.model;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String passwordHash;
    private boolean isLocked;
    private int failedLoginAttempts;
    private boolean studentVerified;
    private ArrayList<Ticket> tickets;

    public User(String userName, String firstName, String lastName, String email, String phoneNumber,
                Date birthDate, String passwordHash, boolean isLocked, int failedLoginAttempts, boolean studentVerified) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.passwordHash = passwordHash;
        this.isLocked = isLocked;
        this.failedLoginAttempts = failedLoginAttempts;
        this.studentVerified = studentVerified;
        this.tickets = new ArrayList<>();
    }

    // Getters and setters
    public String getUserName() { return userName; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public Date getBirthDate() { return birthDate; }
    public String getPasswordHash() { return passwordHash; }
    public boolean isLocked() { return isLocked; }
    public int getFailedLoginAttempts() { return failedLoginAttempts; }
    public boolean isStudentVerified() { return studentVerified; }
    public ArrayList<Ticket> getTickets() { return tickets; }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setFailedLoginAttempts(int attempts) {
        failedLoginAttempts = attempts;
    }

    public void setStudentVerified(boolean verified) {
        studentVerified = verified;
    }
}
/*
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
            System.out.println("Username already exists. Please choose a different username.");
        }
    }
}
        */
