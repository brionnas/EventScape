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
        userList.add(new User("pplante", "Portia", "Plante", "random@email.sc.edu", 
        "2309553344", "123password", false, 0, true));

        JSONArray jsonUsers = new JSONArray();

        for (User user : userList) {
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

       /*  JSONArray jsonTickets = new JSONArray();
        for (Ticket ticket : user.getTickets()) { //Waiting for Ticket stub class to call for it
            jsonTickets.add(ticket.toString());
        }
        userDetails.put("tickets", jsonTickets); */

        return userDetails;
    }

    public static void main(String[] args) {
        DataWriter.saveUsers();
    }
}