package com.model;

	
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants { 

    public static void saveUsers() {
       // Users users = Users.getInstance(); //Needs a Users.java singleton
       // ArrayList<User> userList = users.getUsers();


        ArrayList<User> userList = new ArrayList<>();

        try{
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            Date birthDate = formatter.parse("01-01-2000");

            userList.add(new User("pplante", "Portia", "Plante", 
            "random@email.sc.edu", "2309553344", birthDate, 
            "123password", false, 0, true));
        } 
        catch (ParseException e) {
            e.printStackTrace();  // Or handle it in some user-friendly way
    }
        
        User user = userList.get(0);

        Ticket ticket1 = new Ticket("ba4cce17-a624-4d2b-80fe-eae11665feb6", "B7", "Confirmed");
        Ticket ticket2 = new Ticket("d3a88e28-3d23-4e95-a2aa-1234567890ab", "C3", "Pending");

        user.setTickets(new ArrayList<>());
        user.getTickets().add(ticket1);
        user.getTickets().add(ticket2);

        JSONArray jsonUsers = new JSONArray();

        for (User currentUser : userList) {
            jsonUsers.add(getUserJSON(user));
        }

        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_NAME, user.getUserName());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PHONE_NUMBER, user.getPhoneNumber());
        
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd,yyyy");
        userDetails.put(USER_BIRTH_DATE, formatter.format(user.getBirthDate()));

        userDetails.put(USER_PASSWORD_HASH, user.getPasswordHash());
        userDetails.put(USER_IS_LOCKED, user.getIsLocked());
        userDetails.put(USER_FAILED_LOGIN_ATTEMPTS, user.getFailedLoginAttempts());
        userDetails.put(USER_STUDENT_VARIFIED, user.getStudentVerified());
        userDetails.put(USER_TICKETS, user.getTickets());

        JSONArray ticketArray = new JSONArray();
        if (user.getTickets() != null){ 
            for(Ticket ticket : user.getTickets()){
       JSONObject ticketJSON = new JSONObject();

                JSONArray confirmationArray = new JSONArray();

                ticketJSON.put("ticketConfirmation", ticket.getTicketConfirmation());
                ticketJSON.put("seatNum", ticket.getSeatNum());
                ticketJSON.put("status", ticket.getStatus().toString());

                ticketArray.add(ticketJSON);
            }
        }
        userDetails.put("tickets", ticketArray);
        return userDetails;
    }

    public static void main(String[] args) {
        DataWriter.saveUsers();
    }
}