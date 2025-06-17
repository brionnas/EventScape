package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Ticket {
    private UUID ticketConfirmation; // UUID string
    private UUID eventId;
    private TicketStatus status;
    private ArrayList<Person> people; 

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

    // Getters and setters
    public UUID getTicketConfirmation() { return ticketConfirmation; }
    public String getStatus() { return status.toString(); }
    public ArrayList<Person> getPeople() {return people; }
    public UUID getEventId() { return eventId; }

    public String toString() {
        return "\nTicket: " + ticketConfirmation + ", " + status + ", People: " + people;
    }

    public Object getUserId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserId'");
    }

    public Event getEvent() {
        return EventList.getInstance().getEventById(eventId);
    }

    public Object getSeatNum() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSeatNum'");
    }
}

