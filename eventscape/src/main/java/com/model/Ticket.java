package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Ticket {
    private UUID ticketConfirmation; // UUID string
    private UUID eventId;
    private UUID userId; 
    private TicketStatus status;
    private ArrayList<Person> people;
    private String ticketId; 

    public Ticket(UUID ticketConfirmation, UUID eventId, TicketStatus status, ArrayList<Person> people) {
        this.ticketConfirmation = ticketConfirmation;
        this.eventId = eventId;
        this.status = status;
        this.people = people;
        try {
            this.status = TicketStatus.PENDING;
        } catch (Exception e) {
            this.status = TicketStatus.PENDING;
        }
    }

    public Ticket(Event event, User currentUser) {
         this.ticketConfirmation = UUID.randomUUID();
        this.eventId = event.getEventId();
        this.status = TicketStatus.CONFIRMED; 
        this.people = new ArrayList<>();
    }

    // Getters and setters
    public UUID getTicketConfirmation() { return ticketConfirmation; }
    public String getStatus() { return status.toString(); }
    public ArrayList<Person> getPeople() {return people; }
    public UUID getEventId() { return eventId; }

    public String toString() {
        return "\nTicket: " + ticketConfirmation + ", " + status + ", People: " + people;
    }

    public Object getUserId() {
        return userId;
    }

    public Event getEvent() {
        return EventList.getInstance().getEventById(eventId);
    }

     public String getTicket() {
        return ticketConfirmation.toString();
    }

    public String getSeatNum() {
        return "General Admission"; 
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public String getTicketId() {
        return this.ticketId != null ? this.ticketId : "UNKNOWN_ID"; 
    }


}

