package com.model;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.UUID;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class SimplifiedUI {
    public SimplifiedUI() {

    }

    public void run() {
        //scenario1();
        //scenario2();
        //scenario3();
        scenario4();

    }

    public void scenario1() {
        Facade facade = Facade.getInstance();

        User user = facade.login("pplante", "123password");

        if(user == null) {
            System.out.println("The user doesn't exist");
            return;
        }

        System.out.println("Successfully logged in");
        System.out.println(user);
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

    public void scenario3() {
    Scanner sc = new Scanner(System.in);
    try {
        System.out.print("Enter event name: ");
        String name = sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter sub-category: ");
        String subCategory = sc.nextLine();

        System.out.print("Enter date (MM-dd-yyyy): ");
        String dateStr = sc.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date date = formatter.parse(dateStr);

        System.out.print("Enter capacity: ");
        int capacity = Integer.parseInt(sc.nextLine());

        System.out.print("Enter latitude (as text, e.g., 34.000): ");
        String latitude = sc.nextLine();

        System.out.print("Enter longitude (as text, e.g., -81.000): ");
        String longitude = sc.nextLine();

        System.out.print("Enter host username: ");
        String host = sc.nextLine();

        UUID eventId = UUID.randomUUID();

        Event newEvent = new Event(
            eventId.toString(), name, category, subCategory, latitude
        );

        EventList.getInstance().addEvent(newEvent);
        EventList.getInstance().save();

        System.out.println("Event created and saved!");

    } catch (ParseException e) {
        System.out.println("Invalid date format. Use MM-dd-yyyy.");
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    public void scenario4() {
    Facade facade = Facade.getInstance();
    Scanner sc = new Scanner(System.in);

    //login method only for this scenario
    System.out.print("Enter username: ");
    String username = sc.nextLine();
    System.out.print("Enter password: ");
    String password = sc.nextLine();

    User user = facade.login(username, password);
    if (user == null) {
        System.out.println("Invalid credentials");
        return;
    }

    ArrayList<Event> events = DataLoader.loadEvents();
    if (events.isEmpty()) {
        System.out.println("No events available.");
        return;
    }

    System.out.println("\n Available Events:");
    for (int i = 0; i < events.size(); i++) {
        Event e = events.get(i);
        System.out.println(i + 1 + ". " + e.getName() + " | ID: " + e.getEventId() + " | Capacity Left: " + e.getTicketsLeft());
    }

    // Choose event
    System.out.print("\nSelect event number to buy ticket: ");
    int choice = Integer.parseInt(sc.nextLine()) - 1;
    if (choice < 0 || choice >= events.size()) {
        System.out.println("Invalid event selection.");
        return;
    }

    Event selected = events.get(choice);

    if (selected.getTicketsLeft() <= 0) {
        System.out.println("No tickets left.");
        return;
    }

    Ticket ticket = new Ticket(UUID.randomUUID(), TicketStatus.PENDING, new ArrayList<Person>());

    if (user.getTickets() == null)
        user.setTickets(new ArrayList<>());

    user.getTickets().add(ticket);
    selected.setTicketsLeft(selected.getTicketsLeft() - 1);

    UserList.getInstance().save();
    EventList.getInstance().save();

    System.out.println("Ticket purchased! Updated event capacity: " + selected.getTicketsLeft());
}


    public static void main(String[] args){
        (new SimplifiedUI()).run();
    }
    
}
