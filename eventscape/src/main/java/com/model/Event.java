package com.model;

import java.util.Date;
import java.util.List;

public class Event {
    private String eventId;
    private String name;
    private String category;
    private String subCategory;
    private Date date;
    private int capacity;
    private int ticketsLeft;
    private String latitude;
    private String longitude;
    private String host;
    private List<String> attendees;
    private List<String> waitlist;
    private List<String> tickets;
    private List<String> reviews;

    public Event(String eventId, String name, String category, String subCategory,
                 Date date, int capacity, int ticketsLeft, String latitude, String longitude,
                 String host, List<String> attendees, List<String> waitlist,
                 List<String> tickets, List<String> reviews) {
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
    public Event(String eventId, String name, String description, String date, String time, String location) {
    this.eventId = eventId;
    this.name = name;
    this.category = description;
    this.subCategory = "General";
    this.date = new Date(); // or parse `date + " " + time` if needed
    this.capacity = 100;
    this.ticketsLeft = 100;
    this.latitude = "0.0";
    this.longitude = "0.0";
    this.host = location;
    this.attendees = new java.util.ArrayList<>();
    this.waitlist = new java.util.ArrayList<>();
    this.tickets = new java.util.ArrayList<>();
    this.reviews = new java.util.ArrayList<>();
}


    // Getters and setters
    public String getEventId() { 
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
    public List<String> getAttendees() {
         return attendees; 
        }
    public List<String> getWaitlist() {
         return waitlist;
         }
    public List<String> getTickets() {
         return tickets; 
        }
    public List<String> getReviews() {
         return reviews;
         }

    public void setTicketsLeft(int ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }       
    public void setWaitlist(List<String> waitlist) {
        this.waitlist = waitlist;
    }
    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }
    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
    public void setEventId(String eventId) {
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
}