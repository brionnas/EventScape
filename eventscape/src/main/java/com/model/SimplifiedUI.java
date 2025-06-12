package com.model;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.UUID;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimplifiedUI {
    public SimplifiedUI() {

    }

    public void run() {
        scenario1();
        //scenario2();
        //scenario3();

    }

    public void scenario1() {
        Facade facade = Facade.getInstance();

        User user = facade.login("smithadam", "abc123hash");

        if(user == null) {
            System.out.println("The user doesn't exist");
            return;
        }

        System.out.println("Successfully logged in");
        System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName());

        //let's show all the events
        List<Event> events = Facade.getInstance().getAllEvents();

        System.out.println("\nHere are all the events");

        for(Event event : events){
            System.out.println("- " + event.getName());
        }

        System.out.println("\nYou have purchased the following tickets");
        ArrayList<Ticket> tickets = user.getTickets();

        for(Ticket ticket : tickets) {
            Event event = ticket.getEvent();
            ArrayList<Person> people  = ticket.getPeople();
            String attendees = "";

            for(Person person : people) {
                attendees += person.getFirstName() + " " + person.getLastName() + ", ";
            }

            System.out.println("- " + event.getName() + " Attendees: " + attendees);
        }
    }

    public void scenario2(){
        Facade facade = Facade.getInstance();
        if(facade.addUser("amstih", "Amy", "Smith", "asmith@email.com", "203-445-4433",Utilities.toDate("2000-03-03") , "123")){
            System.out.println("User successfully added");

            facade.logout();

            User user = facade.login("amstih", "123");

            if(user == null) {
                System.out.println("The user doesn't exist");
                return;
            }

            System.out.println("Successfully logged in");
            System.out.println(user);
        } else {
            System.out.println("Sorry");
        }  
    }


    public static void main(String[] args){
        (new SimplifiedUI()).run();
    }
    
}
