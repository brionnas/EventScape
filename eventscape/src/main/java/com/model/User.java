package com.model;
import java.util.*;

public class User{
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

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public Date getBirthDate(){
        return birthDate;
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    public boolean getIsLocked(){
        return isLocked;
    }

    public int getFailedLoginAttempts(){
        return failedLoginAttempts;
    }

    public boolean getStudentVerified(){
        return false;
    }

    public ArrayList<Ticket> getTickets(){
        return tickets;
    }

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
        + "Student Verified: " + studentVerified + "" + "\n";
    }

    public void setTickets(ArrayList<Ticket> tickets) {
     this.tickets = tickets;
    }

    public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
    }
}