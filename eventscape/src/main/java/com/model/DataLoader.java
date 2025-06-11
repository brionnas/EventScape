package com.model;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
;

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

    public static ArrayList<Ticket> getTickets()
    {
        ArrayList<Ticket> Tickets = new ArrayList<Ticket>();

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

                JSONArray ticketsJSON = (JSONArray) ticketsJSON.get(USER_TICKETS);
                ArrayList<Ticket> tickets = new ArrayList<Ticket>();

                for (int j=0; j < ticketsJSON.size(); j++) {
                    JSONObject ticketJSON = (JSONObject)ticketsJSON.get(j); 
                    String ticketConfirmation = (String) ticketJSON.get(USER_TICKET_CONFIRMATION); 
                    String status = (String) ticketJSON.get("status"); 
                    String seatNum = (String) ticketJSON.get("seatNum");
                    Ticket myTicket = new Ticket(ticketConfirmation, status, seatNum);
                    tickets.add(myTicket);
                }

             tickets.add(new Ticket(getTicketConfirmation, getSeatNum, getStatus));


            }

        } catch (Exception e) { 
            e.printStackTrace();
        }



        return tickets;
     }


    public static ArrayList<Event> loadEvents() {
    ArrayList<Event> events = new ArrayList<>();

    try {
        FileReader reader = new FileReader(EVENT_FILE_NAME);
        JSONArray eventsJSON = (JSONArray) new JSONParser().parse(reader);

        for (Object obj : eventsJSON) {
            JSONObject eventJSON = (JSONObject) obj;

            UUID eventId = UUID.fromString((String) eventJSON.get("eventId"));
            String name = (String) eventJSON.get("name");
            String category = (String) eventJSON.get("category");
            String subCategory = (String) eventJSON.get("subCategory");

            String dateStr = (String) eventJSON.get("date");
            Date date = Utilities.toDate(dateStr); // assumes MM-dd-yyyy

            int capacity = ((Long) eventJSON.get("capacity")).intValue();
            int ticketsLeft = ((Long) eventJSON.get("ticketsLeft")).intValue();

            String latitude = (String) eventJSON.get("latitude");
            String longitude = (String) eventJSON.get("longitude");
            String host = (String) eventJSON.get("host");

            // Optional arrays (can be empty)
            ArrayList<Person> attendees = new ArrayList<Person>();
            ArrayList<Ticket> waitlist = new ArrayList<Ticket>();
            ArrayList<Ticket> tickets = new ArrayList<>();
            ArrayList<Review> reviews = new ArrayList<>();

            JSONArray ticketsJSON = (JSONArray) eventJSON.get("tickets");
            for (int j=0; j < ticketsJSON.size(); j++) {
                    UUID ticketConfirmation = UUID.fromString((String) ticketsJSON.get(j)); 
                    //tickets.add(myTicket);
                }

            JSONArray attendeesJSON = (JSONArray) eventJSON.get("attendees");
            for (int j=0; j < attendeesJSON.size(); j++) {
                    JSONObject attendee = (JSONObject)attendeesJSON.get(j); 
                    String status = (String) attendeesJSON.get("status"); 
                    String seatNum = (String) attendeesJSON.get("seatNum");
                    Attendeed myAttendee = new Ticket(USER_TICKET_CONFIRMATION, status, seatNum);
                    attendee.add(myAttendee);
                }
            
            JSONArray waitlistJSON = (JSONArray) ticketsJSON.get("tickets"); 
            for (int j=0; j < ticketsJSON.size(); j++) {
                UUID ticketConfirmation = UUID.fromString((String) ticketsJSON.get(j)); 
                    //tickets.add(myTicket);
            }

            JSONArray reviewsJSON = (JSONArray) reviewsJSON.get("reviews"); 
            for (int j=0; j < ticketsJSON.size(); j++) {
                UUID ticketConfirmation = UUID.fromString((String) ticketsJSON.get(j)); 
                //tickets.add(myTicket); 
            }
            

            Event event = new Event(eventId, name, category, subCategory, date,
                    capacity, ticketsLeft, latitude, longitude, host,
                    attendees, waitlist, tickets, reviews);

            events.add(event);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return events;
}
    

    public static void main(String[] args) {
    ArrayList<User> users = DataLoader.getUsers();

        for(User user : users) {
            System.out.println(user);
        } 
    }
}

