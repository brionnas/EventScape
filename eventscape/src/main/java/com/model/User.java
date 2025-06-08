package com.model;

import java.util.ArrayList;
import java.util.Date;

import java.util.UUID;

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
    private ArrayList<UUID> favorites;

    public User(String userName, String firstName, String lastName,
                String email, String phoneNumber, Date birthDate,
                String passwordHash){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.passwordHash = passwordHash;
        this.isLocked = false;
        this.failedLoginAttempts = 0;
        this.studentVerified = false;
        this.tickets =  new ArrayList<>();
    }

    User(String userName, String firstName, String lastName,
    String email, String phoneNumber, Date birthDate, String passwordHash, 
    boolean isLocked, int failedAttempts, boolean studentVerified, ArrayList<Ticket> tickets){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.passwordHash = passwordHash;
        this.isLocked = isLocked;
        this.failedLoginAttempts = failedAttempts;
        this.studentVerified = studentVerified;
        this.tickets = tickets;
}

    public String getUserName(){
        return userName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
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
    public ArrayList<UUID> getFavorites(){
        return favorites;
    }

    // Setters
    public void setTickets(ArrayList<Ticket> tickets) { 
        this.tickets = tickets;
     }
    public void setBirthDate(Date birthDate) { 
        this.birthDate = birthDate; 
    }
    public void setFavorites(ArrayList<UUID> favorites){
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return 
        firstName + "  " 
        + lastName  + " " + "\n"
        + "Phone Number: " + phoneNumber + ""  + "\n" 
        + "Is Locked: " + isLocked + ""  + "\n"
        + "UserName: " + userName + ""  + "\n"
        + "Failed Login Attempts: " + failedLoginAttempts + ""  + "\n"
        + "Birthday: " + birthDate + "" + "\n"
        + "Email: " + email + "" + "\n"
        + "Password Hash: " + passwordHash + "" + "\n"
        + "Student Verified: " + studentVerified + "" + "\n"
        + "Tickets: " + tickets;

    }
}
