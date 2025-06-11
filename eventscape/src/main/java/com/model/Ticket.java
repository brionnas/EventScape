package com.model;

public class Ticket {
    private String ticketConfirmation; // UUID string
    private String seatNum;
    private TicketStatus status;

    public Ticket(String ticketConfirmation, String seatNum, String statusStr) {
        this.ticketConfirmation = ticketConfirmation;
        this.seatNum = seatNum;
        try {
            this.status = TicketStatus.valueOf(statusStr.toUpperCase());
        } catch (Exception e) {
            this.status = TicketStatus.PENDING;
        }
    }

    // Getters and setters
    public String getTicketConfirmation() { return ticketConfirmation; }
    public String getSeatNum() { return seatNum; }
    public String getStatus() { return status.toString(); }

    public String toString() {
        return "\nTicket: " + ticketConfirmation + ", " + seatNum + ", " + status;
    }

    public Object getUserId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserId'");
    }

    public Object getTicketId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTicketId'");
    }
}

