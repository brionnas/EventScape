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
    public TicketStatus getStatus() { return status; }

    public void setStatus(TicketStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Ticket " + ticketConfirmation + " Seat: " + seatNum + " Status: " + status;
    }
}

