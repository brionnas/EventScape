package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class TicketList {
    private static TicketList instance;
    private ArrayList<Ticket> tickets;

    private TicketList() {
        tickets = DataLoader.getTickets();
    }

    public static TicketList getInstance() {
        if (instance == null) {
            instance = new TicketList();
        }
        return instance;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public ArrayList<Ticket> getTicketsByUser(UUID userId) {
        ArrayList<Ticket> result = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getUserId().equals(userId)) {
                result.add(t);
            }
        }
        return result;
    }

    public Ticket getTicketById(UUID ticketId) {
        for (Ticket t : tickets) {
            if (t.getTicketId().equals(ticketId)) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<Ticket> getAllTickets() {
        return tickets;
    }

    public boolean saveTickets() {
        //DataWriter.saveTickets();

        return true;
    }
}

