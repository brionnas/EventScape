package com.model;

	
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Date;
import java.io.FileReader;
import java.util.UUID;


//public class DataWriter extends DataConstants { 
public class DataWriter{

    public static void saveUsers() {
        Users users = Users.getInstance(); //Needs a Users.java singleton
        ArrayList<User> userList = users.getUsers();

        JSONArray jsonUsers = new JSONArray();

        for (User user : userList) {
            jsonUsers.add(getUserJSON(user));
        }

        try (FileWriter file = new FileWriter("waiting for constants")) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put("waiting for constants", user.getUserName());
        userDetails.put("waiting for constants", user.getFirstName());
        userDetails.put("waiting for constants", user.getLastName());
        userDetails.put("waiting for constants", user.getEmail());
        userDetails.put("waiting for constants", user.getPhoneNumber());
        userDetails.put("waiting for constants", user.getBirthDate());
        userDetails.put("waiting for constants", user.getPasswordHash());
        userDetails.put("waiting for constants", user.getIsLocked());
        userDetails.put("waiting for constants", user.getFailedLoginAttempts());
        userDetails.put("waiting for constants", user.getStudentVerified());

        JSONArray jsonTickets = new JSONArray();
        for (Ticket ticket : user.getTickets()) { //Waiting for Ticket stub class to call for it
            jsonTickets.add(ticket.toString());
        }
        userDetails.put("waiting for constants", jsonTickets);

        return userDetails;
    }

    public static void main(String[] args) {
        saveUsers();
    }
}