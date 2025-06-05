package com.model;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import java.util.jar.Attributes.Name;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
                String phoneNumber = (String) personJSON.get(USER_PHONE_NUMBER);
                String email = (String) personJSON.get("email");
                Date birthDate = Utilities.toDate((String) personJSON.get("birthDate")); 
                String passwordHash = (String) personJSON.get("passwordHash");
                boolean  isLocked2 = (Boolean) personJSON.get("isLocked");
                int failedAttempts = ((Long) personJSON.get("failedLoginAttempts")).intValue();
                boolean studentVerified = (Boolean) personJSON.get("studentVerified");

                JSONArray ticketsJSON = (JSONArray) personJSON.get(USER_TICKETS);
                ArrayList<Ticket> tickets = new ArrayList<>();

                for (int j=0; j < ticketsJSON.size(); j++) {
                    JSONObject ticketJSON = (JSONObject)ticketsJSON.get(j); 
                    String ticketConfirmation = (String) ticketJSON.get(USER_TICKET_CONFIRMATION); 
                    String status = (String) ticketJSON.get("status"); 
                    String seatNum = (String) ticketJSON.get("seatNum");
                    Ticket myTicket = new Ticket(ticketConfirmation, status, seatNum);
                    tickets.add(myTicket);
                }

             users.add(new User( userName, userFirstName, userLastName, email, phoneNumber, birthDate, passwordHash, isLocked2, failedAttempts, studentVerified, tickets));


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

    public static List<Event> getEvents() {
        // This method is not implemented yet.        
        throw new UnsupportedOperationException("Unimplemented method 'getEvents'");
    }



}
