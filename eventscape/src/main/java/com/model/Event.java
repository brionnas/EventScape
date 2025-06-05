package com.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Event {
    private UUID eventId;
    private String name;
    private Category category;
    private String subCategory;
    private Date date;
    private int capacity;
    private int ticketsLeft;
    private String latitude;
    private String longitude;
    private UUID host;
    private List<UUID> attendees;
    private List<UUID> waitlist;
    private List<UUID> tickets;
    private List<UUID> reviews;

    public Event(UUID eventId, String name, Category category, String subCategory, Date date,
                 int capacity, int ticketsLeft, String latitude, String longitude, UUID host,
                 List<UUID> attendees, List<UUID> waitlist, List<UUID> tickets, List<UUID> reviews) {
        this.eventId = eventId;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.date = date;
        this.capacity = capacity;
        this.ticketsLeft = ticketsLeft;
        this.latitude = latitude;
        this.longitude = longitude;
        this.host = host;
        this.attendees = attendees;
        this.waitlist = waitlist;
        this.tickets = tickets;
        this.reviews = reviews;
    }

    // Getters and setters here

    public UUID getEventId() { 
        return eventId; 
    }
    public String getName() { 
        return name; 
    }
    public Category getCategory() { 
        return category; 
    }
    public String getSubCategory() { 
        return subCategory; 
    }
    public Date getDate() { 
        return date; 
    }
    public int getCapacity() { 
        return capacity; 
    }
    public int getTicketsLeft() { 
        return ticketsLeft; 
    }
    public String getLatitude() { 
        return latitude; 
    }
    public String getLongitude() { 
        return longitude; 
    }
    public UUID getHost() { 
        return host; 
    }
    public List<UUID> getAttendees() { 
        return attendees; 
    }
    public List<UUID> getWaitlist() { 
        return waitlist; 
    }
    public List<UUID> getTickets() { 
        return tickets; 
    }
    public List<UUID> getReviews() { 
        return reviews; 
    }

    // Setters
    public void setEventId(UUID eventId) { 
        this.eventId = eventId; 
    }   
    public void setName(String name) { 
        this.name = name; 
    }
    public void setCategory(Category category) { 
        this.category = category; 
    }
    public void setSubCategory(String subCategory) { 
        this.subCategory = subCategory; 
    }
    public void setDate(Date date) { 
        this.date = date; 
    }
    public void setCapacity(int capacity) { 
        this.capacity = capacity; 
    }
    public void setTicketsLeft(int ticketsLeft) { 
        this.ticketsLeft = ticketsLeft; 
    }

    public void setLatitude(String latitude) { 
        this.latitude = latitude; 
    }
    public void setLongitude(String longitude) { 
        this.longitude = longitude; 
    }
    public void setHost(UUID host) { 
        this.host = host; 
    }
    public void setAttendees(List<UUID> attendees) { 
        this.attendees = attendees; 
    }

    public void setWaitlist(List<UUID> waitlist) { 
        this.waitlist = waitlist; 
    }
    public void setTickets(List<UUID> tickets) { 
        this.tickets = tickets; 
    }
    public void setReviews(List<UUID> reviews) { 
        this.reviews = reviews; 
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", subCategory='" + subCategory + '\'' +
                ", date=" + date +
                ", capacity=" + capacity +
                ", ticketsLeft=" + ticketsLeft +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", host=" + host +
                ", attendees=" + attendees +
                ", waitlist=" + waitlist +
                ", tickets=" + tickets +
                ", reviews=" + reviews +
                '}';
    }
    
}
