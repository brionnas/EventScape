package com.model;

public enum TicketStatus {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    UNCONFIRMED("Unconfirmed"),
    WAITLIST("Waitlist"),
    CANCELLED("Cancelled");

    private final String displayName;

    TicketStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
