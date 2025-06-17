package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    public static void saveUsers() {
        // Previously hardcoded user data for testing:
        /*
        ArrayList<User> userList = new ArrayList<>();

        try{
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            Date birthDate = formatter.parse("01-01-2000");

            userList.add(new User("pplante", "Portia", "Plante", 
                "random@email.sc.edu", "2309553344", birthDate, 
                "123password", false, 0, true));
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }

        User user = userList.get(0);

        Ticket ticket1 = new Ticket("ba4cce17-a624-4d2b-80fe-eae11665feb6", "B7", "Confirmed");
        Ticket ticket2 = new Ticket("d3a88e28-3d23-4e95-a2aa-1234567890ab", "C3", "Pending");

        user.setTickets(new ArrayList<>());
        user.getTickets().add(ticket1);
        user.getTickets().add(ticket2);

        user.setFavorites(new ArrayList<>());
        user.getFavorites().add(UUID.fromString("6584ff0a-459b-4827-9132-68b86e839455"));
        */
        ArrayList<User> userList = new ArrayList<>(UserList.getInstance().getUsers());

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

    public static void saveEvents() {
        ArrayList<Event> events = new ArrayList<>(EventList.getInstance().getEvents());
        JSONArray jsonEvents = new JSONArray();

        for (Event event : events) {
            JSONObject obj = new JSONObject();
            obj.put("eventId", event.getEventId().toString());
            obj.put("name", event.getName());
            obj.put("category", event.getCategory());
            obj.put("subCategory", event.getSubCategory());
            obj.put("date", event.getDate().toString());
            obj.put("latitude", event.getLatitude());
            obj.put("longitude", event.getLongitude());
            obj.put("capacity", event.getCapacity());
            obj.put("ticketsLeft", event.getTicketsLeft());
            obj.put("host", event.getHost());

            jsonEvents.add(obj);
        }

        try (FileWriter file = new FileWriter(EVENT_FILE_NAME)) {
            file.write(jsonEvents.toJSONString());
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

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        userDetails.put(USER_BIRTH_DATE, formatter.format(user.getBirthDate()));

        userDetails.put(USER_PASSWORD_HASH, user.getPasswordHash());
        userDetails.put(USER_IS_LOCKED, user.getIsLocked());
        userDetails.put(USER_FAILED_LOGIN_ATTEMPTS, user.getFailedLoginAttempts());
        userDetails.put(USER_STUDENT_VARIFIED, user.getStudentVerified());

        JSONArray ticketArray = new JSONArray();
        if (user.getTickets() != null) {
            for (Ticket ticket : user.getTickets()) {
                ticketArray.add(ticket.getTicketConfirmation().toString());
            }
        }
        userDetails.put("tickets", ticketArray);

        JSONArray favoritesArray = new JSONArray();
        if (user.getFavorites() != null){
            for(UUID id : user.getFavorites()){
                favoritesArray.add(id.toString());
            }
        }
        userDetails.put("favorites", favoritesArray);

        return userDetails;
    }

    public static void saveTickets() {
        ArrayList<Ticket> tickets = TicketList.getInstance().getAllTickets();
        JSONArray jsonTickets = new JSONArray();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

        for (Ticket ticket : tickets) {
            JSONObject ticketJSON = new JSONObject();
            ticketJSON.put("ticketUUID", ticket.getTicketConfirmation().toString());
            ticketJSON.put("eventId", ticket.getEventId().toString());
            ticketJSON.put("ticketStatus", ticket.getStatus().toString());

            JSONArray peopleArray = new JSONArray();
            if (ticket.getPeople() != null) {
                for (Person person : ticket.getPeople()) {
                    JSONObject personJSON = new JSONObject();
                    personJSON.put("firstName", person.getFirstName());
                    personJSON.put("lastName", person.getLastName());
                    personJSON.put("birthDate", formatter.format(person.getBirthDate()));
                    peopleArray.add(personJSON);
                }
            }

            ticketJSON.put("people", peopleArray);
            jsonTickets.add(ticketJSON);
        }

        try (FileWriter file = new FileWriter(TICKET_FILE_NAME)) {
            file.write(jsonTickets.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        DataWriter.saveUsers();
        DataWriter.saveEvents();
        DataWriter.saveTickets();
    }
} 
