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
                    UUID ticketId = UUID.fromString((String)ticketsJSON.get(j)); 
                    Ticket ticket = TicketList.getInstance().getTicketById(ticketId);
                    tickets.add(ticket);
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
         ArrayList<Ticket> allTickets = new ArrayList<Ticket>();

        try { 
            FileReader reader = new FileReader(TICKET_FILE_NAME);
            JSONArray ticketsJSON = (JSONArray)new JSONParser().parse(reader); 

            for (int i=0; i < ticketsJSON.size(); i++) {
                JSONObject ticketJSON = (JSONObject)ticketsJSON.get(i); 
                UUID ticketUUID = UUID.fromString((String) ticketJSON.get("ticketUUID")); 
                UUID eventId = UUID.fromString((String) ticketJSON.get("eventId"));
                
                // Process people array
                ArrayList<Person> people = new ArrayList<>();
                JSONArray peopleJSON = (JSONArray) ticketJSON.get("people");
                if (peopleJSON != null) {
                    for (int j = 0; j < peopleJSON.size(); j++) {
                        JSONObject personJSON = (JSONObject) peopleJSON.get(j);
                        String firstName = (String) personJSON.get("firstName");
                        String lastName = (String) personJSON.get("lastName");
                        Date birthDate = Utilities.toDate((String) personJSON.get("birthDate"));
                        Person person = new Person(firstName, lastName, birthDate);
                        people.add(person);
                    }
                }
                
                Ticket ticket = new Ticket(ticketUUID, eventId, people);
                allTickets.add(ticket);
            }

        } catch (Exception e) { 
            e.printStackTrace();
        }

        return allTickets;
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
                if (ticketsJSON != null) {
                    for (int j = 0; j < ticketsJSON.size(); j++) {
                        // If tickets are stored as UUIDs, get the ticket from TicketList
                        UUID ticketId = UUID.fromString((String) ticketsJSON.get(j));
                        Ticket ticket = TicketList.getInstance().getTicketById(ticketId);
                        if (ticket != null) {
                            tickets.add(ticket);
                        }
                        
                        // Alternative: If tickets are stored as full objects in JSON
                        JSONObject ticketJSON = (JSONObject) ticketsJSON.get(j);
                        UUID ticketConfirmation = UUID.fromString((String) ticketJSON.get("ticketConfirmation"));
                        ArrayList<Person> status = (ArrayList<Person>) ticketJSON.get("status");
                        UUID seatNum = (UUID) ticketJSON.get("seatNum");
                        Ticket ticket2 = new Ticket(ticketConfirmation, seatNum, status);
                        tickets.add(ticket);
                    }
                }
                
                // Process attendees array
                JSONArray attendeesJSON = (JSONArray) eventJSON.get("attendees");
                if (attendeesJSON != null) {
                    for (int j = 0; j < attendeesJSON.size(); j++) {
                        JSONObject attendeeJSON = (JSONObject) attendeesJSON.get(j);
                        //Create Person object from JSON and add to attendees
                        String firstName = (String) attendeeJSON.get("firstName");
                        String lastName = (String) attendeeJSON.get("lastName");
                        Person attendee = new Person(firstName, lastName, date);
                        attendees.add(attendee);
                    }
                }
                
                // Process waitlist array
                JSONArray waitlistJSON = (JSONArray) eventJSON.get("waitlist");
                if (waitlistJSON != null) {
                    for (int j = 0; j < waitlistJSON.size(); j++) {
                        // If waitlist contains ticket UUIDs
                        UUID ticketId = UUID.fromString((String) waitlistJSON.get(j));
                        Ticket ticket = TicketList.getInstance().getTicketById(ticketId);
                        if (ticket != null) {
                            waitlist.add(ticket);
                        }
                        
                        //Alternative: If waitlist contains full ticket objects
                        JSONObject waitlistTicketJSON = (JSONObject) waitlistJSON.get(j);
                        UUID ticketConfirmation = UUID.fromString((String) waitlistTicketJSON.get("ticketConfirmation"));
                        ArrayList<Person> status = (ArrayList<Person>) waitlistTicketJSON.get("status");
                        UUID seatNum = (UUID) waitlistTicketJSON.get("seatNum");
                        Ticket waitlistTicket = new Ticket(ticketConfirmation, seatNum, status);
                        waitlist.add(waitlistTicket);
                    }
                }

                // Process reviews array
                JSONArray reviewsJSON = (JSONArray) eventJSON.get("reviews");
                if (reviewsJSON != null) {
                    for (int j = 0; j < reviewsJSON.size(); j++) {
                        JSONObject reviewJSON = (JSONObject) reviewsJSON.get(j);
                        String reviewText = (String) reviewJSON.get("reviewText");
                        int rating = ((Long) reviewJSON.get("rating")).intValue();
                        String comment = (String) reviewJSON.get("comment");
                        Review review = new Review(reviewText, comment, rating);
                        reviews.add(review);
                    }
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

