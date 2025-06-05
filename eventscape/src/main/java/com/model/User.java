package com.model;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private Date birthDate;
    private final String passwordHash;
    private final boolean isLocked;
    private final int failedLoginAttempts;
    private final boolean studentVerified;
    private ArrayList<Ticket> tickets;

    
    public User(String userName, String firstName, String lastName,
                String email, String phoneNumber, Date birthDate,
                String passwordHash, boolean isLocked, int failedLoginAttempts, boolean studentVerified) {
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

    // Getters
    public String getUserName() {
         return userName; 
        }
    public String getFirstName() {
         return firstName; 
        }
    public String getLastName() {
         return lastName;
         }
    public String getEmail() { 
        return email;
     }
    public String getPhoneNumber() {
         return phoneNumber; 
        }
    public Date getBirthDate() {
         return birthDate;
         }
    public String getPasswordHash() {
         return passwordHash; 
        }
    public boolean getIsLocked() {
         return isLocked; 
        }
    public int getFailedLoginAttempts() {
         return failedLoginAttempts;
         }
    public boolean getStudentVerified() { 
        return studentVerified; 
    }
    public ArrayList<Ticket> getTickets() {
         return tickets; 
        }

    // Setters
    public void setTickets(ArrayList<Ticket> tickets) { 
        this.tickets = tickets;
     }
    public void setBirthDate(Date birthDate) { 
        this.birthDate = birthDate; 
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n" +
                "Username: " + userName + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phoneNumber + "\n" +
                "Birth Date: " + birthDate + "\n" +
                "Locked: " + isLocked + "\n" +
                "Failed Logins: " + failedLoginAttempts + "\n" +
                "Student Verified: " + studentVerified + "\n" +
                "Tickets: " + tickets;
    }
}
