package com.model;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader extends DataConstants {

    public static ArrayList<User> getUsers()
    {
        ArrayList<User> users = new ArrayList<User>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader); 

            for (int i=0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject)peopleJSON.get(i); 
                String userName = (String)personJSON.get(USER_NAME); 
                String userFirstName = (String)personJSON.get(USER_FIRST_NAME); 
                String userLastName = (String)personJSON.get(USER_LAST_NAME); 
                String email = (String) personJSON.get("email");
                String phoneNumber = (String) personJSON.get(USER_PHONE_NUMBER);
                String birthDate = (String) personJSON.get("birthDate");
                String passwordHash = (String) personJSON.get("passwordHash");
                boolean isLocked = (Boolean) personJSON.get("isLocked");
                int failedAttempts = ((Long) personJSON.get("failedLoginAttempts")).intValue();
                boolean studentVerified = (Boolean) personJSON.get("studentVerified");

            users.add(new User(userName, userFirstName, userLastName, 
                                email, phoneNumber, birthDate, passwordHash, 
                                isLocked, failedAttempts, studentVerified));


            }

        } catch (Exception e) { 
            e.printStackTrace();
        }

        return users;
    }
    
    public static void main(String[] args) {
    ArrayList<User> users = DataLoader.getUsers();

    for(User user : users) {
        System.out.println(user);
    }
}


}
