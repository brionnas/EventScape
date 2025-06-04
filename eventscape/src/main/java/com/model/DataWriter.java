package com.model;

	
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class DataWriter extends DataConstants { 

    public static void saveUsers() {
       // Users users = Users.getInstance(); //Needs a Users.java singleton
       // ArrayList<User> userList = users.getUsers();

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("pplante", "Portia", "Plante", 
        "random@email.sc.edu", "2309553344", "2000-01-01", 
        "123password", false, 0, true));
        
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
        userDetails.put("userName", user.getUserName());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put("email", user.getEmail());
        userDetails.put(USER_PHONE_NUMBER, user.getPhoneNumber());
        userDetails.put("birthDate", user.getBirthDate());
        userDetails.put("passwordHash", user.getPasswordHash());
        userDetails.put("isLocked", user.getIsLocked());
        userDetails.put("failedLoginAttempts", user.getFailedLoginAttempts());
        userDetails.put("studentVerified", user.getStudentVerified());

        JSONArray ticketArray = new JSONArray();
        if (user.getTickets() != null){ 
            for(Ticket ticket : user.getTickets()){
       JSONObject ticketJSON = new JSONObject();

                JSONArray confirmationArray = new JSONArray();
                confirmationArray.add(ticket.getTicketConfirmation());

                ticketJSON.put("ticketConfirmation", confirmationArray);
                ticketJSON.put("seatNum", ticket.getSeatNum());
                ticketJSON.put("status", ticket.getStatus());

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