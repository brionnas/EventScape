package com.model;

public class Ticket {
    private String ticketConfirmation;
    private String seatNum;
    private String status;

    public Ticket(String ticketConfirmation, String seatNum, String status) {
        this.ticketConfirmation = ticketConfirmation;
        this.seatNum = seatNum;
        this.status = status;
    }

    public String getTicketConfirmation() { return ticketConfirmation; }
    public String getSeatNum() { return seatNum; }
    public String getStatus() { return status; }

    public String toString() {
        return "\nTicket: " + ticketConfirmation + ", " + seatNum + ", " + status;
    }
}
