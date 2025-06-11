package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Event {
    private UUID eventId;
    private String name;
    private String category;
    private String subCategory;
    private Date date;
    private int capacity;
    private int ticketsLeft;
    private String latitude;
    private String longitude;
    private String host;
    private ArrayList<Person> attendees;
    private ArrayList<Ticket> waitlist;
    private ArrayList<Ticket> tickets;
    private ArrayList<Review> reviews;

    public Event(UUID eventId, String name, String category, String subCategory,
                 Date date, int capacity, int ticketsLeft, String latitude, String longitude,
                 String host, ArrayList<Person> attendees, ArrayList<Ticket> waitlist,
                 ArrayList<Ticket> tickets, ArrayList<Review> reviews) {
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
    public Event(String name, String description, String date, String time, String location) {
    this.eventId = UUID.randomUUID();
    this.name = name;
    this.category = description;
    this.subCategory = "General";
    this.date = new Date(); // or parse `date + " " + time` if needed
    this.capacity = 100;
    this.ticketsLeft = 100;
    this.latitude = "0.0";
    this.longitude = "0.0";
    this.host = location;
    this.attendees = new ArrayList<>();
    this.waitlist = new ArrayList<>();
    this.tickets = new ArrayList<>();
    this.reviews = new ArrayList<>();
}


    // Getters and setters
    public UUID getEventId() { 
        return eventId; 
    }
    public String getName() { 
        return name;
     }
    public String getCategory() {  
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
    public String getHost() { 
        return host;
     }
    public ArrayList<Person> getAttendees() {
         return attendees; 
        }
    public ArrayList<Ticket> getWaitlist() {
         return waitlist;
         }
    public ArrayList<Ticket> getTickets() {
         return tickets; 
        }
    public ArrayList<Review> getReviews() {
         return reviews;
         }

    public void setTicketsLeft(int ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }

    public void setAttendees(ArrayList<Person> attendees) {
        this.attendees = attendees;
    }       
    public void setWaitlist(ArrayList<Ticket> waitlist) {
        this.waitlist = waitlist;
    }
    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCategory(String category) {
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
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }   
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }   
    public void setHost(String host) {
        this.host = host;
    }   
    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", date=" + date +
                ", capacity=" + capacity +
                ", ticketsLeft=" + ticketsLeft +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", host='" + host + '\'' +
                ", attendees=" + attendees +
                ", waitlist=" + waitlist +
                ", tickets=" + tickets +
                ", reviews=" + reviews +
                '}';
    }
    public LocalDateTime getDateTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDateTime'");
    }
    public boolean isAtCapacity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAtCapacity'");
    }
    public float getAverageRating() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAverageRating'");
    }
}