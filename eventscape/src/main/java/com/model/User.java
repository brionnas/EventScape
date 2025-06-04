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

    public User(String userName, String firstName, String lastName,
    String email, String phoneNumber, Date birthDate, String passwordHash, boolean isLocked2, int failedAttempts, boolean studentVerified2){
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
        this.tickets = new ArrayList<>();
    }

    public User(UUID id, String userFirstName, String userLastName, int age, String phoneNumber2) {
        //TODO Auto-generated constructor stub
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
        return firstName + "  " + lastName  + " " + "\n" + email + ""  + "\n" + phoneNumber + ""  + "\n" + birthDate + ""  + "\n"+ 
            passwordHash + ""  + "\n"+ isLocked + ""  + "\n"+ failedLoginAttempts + ""  + "\n"+ studentVerified + ""  + "\n";
            //will have ti implement tickets as well 
    }

    public void setTickets(ArrayList<Ticket> tickets) {
     this.tickets = tickets;
    }

    public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
    }
}