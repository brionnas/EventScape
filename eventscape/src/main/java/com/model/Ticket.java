package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Ticket {
    private UUID ticketConfirmation; // UUID string
    private UUID seatNum;
    private TicketStatus status;
    private ArrayList<Person> people; 

    public Ticket(UUID ticketConfirmation, UUID eventId, ArrayList<Person> people) {
        this.ticketConfirmation = ticketConfirmation;
        this.seatNum = eventId;
        try {
            this.status = TicketStatus.PENDING;
        } catch (Exception e) {
            this.status = TicketStatus.PENDING;
        }
    }

    // Getters and setters
    public UUID getTicketConfirmation() { return ticketConfirmation; }
    public UUID getSeatNum() { return seatNum; }
    public String getStatus() { return status.toString(); }
    public ArrayList<Person> getPeople() {return people; }

    public String toString() {
        return "\nTicket: " + ticketConfirmation + ", " + seatNum + ", " + status + ", People: " + people;
    }

    public Object getUserId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserId'");
    }
}

