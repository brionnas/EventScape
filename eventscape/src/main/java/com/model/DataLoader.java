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
                //int age = ((Long)personJSON.get(USER_AGE)).intValue(); 
                //String phoneNumber = (String)personJSON.get(USER_PHONE_NUMBER); 
                
                users.add(new User(userFirstName, userFirstName, userLastName, "", "", "")); 
            }

            return users; 

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
