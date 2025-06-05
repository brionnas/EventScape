package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.util.Pair;

public class Event {
    private UUID eventId;
    private String name;
    private Category category;
    private String subCategory;
    private LocalDateTime dateTime;
    private int capacity;
    private float averageRating;
    private String favorited;
    private List<User> attendees;
    private List<Ticket> ticketList;
    private List<Ticket> waitList;
    private List<Review> reviews;

    // Constructor
    public Event(UUID eventId, String name, Category category, String subCategory, Date date,
                 int capacity, int ticketsLeft, String latitude, String longitude, UUID host,
                 List<UUID> attendees, List<UUID> waitlist, List<UUID> tickets, List<UUID> reviews) {
        this.eventId = UUID.randomUUID();
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.dateTime = dateTime;
        this.capacity = capacity;
        this.averageRating = 0.0f;
        this.favorited = "";
        this.attendees = new ArrayList<>();
        this.ticketList = new ArrayList<>();
        this.waitList = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    // Constructor with eventId
    public Event(UUID eventId, String name, Category category, Genre subCategory,
                LocalDateTime dateTime, int capacity) {
        this.eventId = eventId;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.dateTime = dateTime;
        this.capacity = capacity;
        this.averageRating = 0.0f;
        this.favorited = "";
        this.attendees = new ArrayList<>();
        this.ticketList = new ArrayList<>();
        this.waitList = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    // Getters
    public UUID getEventId() { return eventId; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public Genre getSubCategory() { return subCategory; }
    public LocalDateTime getDateTime() { return dateTime; }
    public int getCapacity() { return capacity; }
    public float getAverageRating() { return averageRating; }
    public String getFavorited() { return favorited; }
    public List<User> getAttendees() { return attendees; }
    public List<Ticket> getTicketList() { return ticketList; }
    public List<Ticket> getWaitList() { return waitList; }
    public List<Review> getReviews() { return reviews; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setCategory(Category category) { this.category = category; }
    public void setSubCategory(Genre subCategory) { this.subCategory = subCategory; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setFavorited(String favorited) { this.favorited = favorited; }


    /**
     * Get detailed information about the event
     * 
     */
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Event: ").append(name).append("\n");
        details.append("Category: ").append(category).append(" - ").append(subCategory).append("\n");
        details.append("Date & Time: ").append(dateTime).append("\n");
        details.append("Capacity: ").append(capacity).append("\n");
        details.append("Current Attendees: ").append(attendees.size()).append("\n");
        details.append("Average Rating: ").append(averageRating).append("/5\n");
        details.append("Available Tickets: ").append(capacity - attendees.size()).append("\n");
        details.append("Waitlist Size: ").append(waitList.size()).append("\n");
        return details.toString();
    }



    /**
     * Remove a user from the event
     *@return true if successfully removed, false otherwise
     */
    public boolean removeUserFromEvent(User user) {
        if (attendees.remove(user)) {
            // Remove their ticket as well
            ticketList.removeIf(ticket -> ticket.getTicketConfirmation().contains(user.getUserName()));
            updateWaitlist();
            return true;
        }
        return false;
    }

    /**
     * Update the waitlist if there are available spots
     */
    public Pair<Boolean, Integer> updateWaitlist() {
        if (attendees.size() < capacity && !waitList.isEmpty()) {
            // Move first person from waitlist to confirmed
            Ticket waitlistTicket = waitList.remove(0);
            waitlistTicket.setStatus("Confirmed");
            ticketList.add(waitlistTicket);
            
            // Update waitlist positions
            for (int i = 0; i < waitList.size(); i++) {
                
            }
            
            return new Pair<>(true, -1); 
        }
        return new Pair<>(false, waitList.size());
    }

    /**
     * Add a user to the event
     * @return true if added successfully, false if event is full
     */
    public boolean addUserToEvent(User user) {
        if (attendees.size() < capacity) {
            attendees.add(user);
            return true;
        }
        return false;
    }

    /**
     * Add a ticket to the event
     */
    public void addTicket(Ticket ticket) {
        if (attendees.size() < capacity) {
            ticketList.add(ticket);
        } else {
            waitList.add(ticket);
        }
    }

    /**
     * Add a review to the event and update average rating
     * @param review the review to add
     */
    public void addReview(Review review) {
        reviews.add(review);
        calculateAverageRating();
    }

    /**
     * Calculate and update the average rating based on all reviews
     */
    private void calculateAverageRating() {
        if (reviews.isEmpty()) {
            averageRating = 0.0f;
            return;
        }
        
        float totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }
        averageRating = totalRating / reviews.size();
    }

    /**
     * Check if the event is at capacity
     * @return true if at capacity, false otherwise
     */
    public boolean isAtCapacity() {
        return attendees.size() >= capacity;
    }

    /**
     * Check if the event has passed
     * @return true if event date has passed, false otherwise
     */
    public boolean isPastEvent() {
        return LocalDateTime.now().isAfter(dateTime);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", subCategory=" + subCategory +
                ", dateTime=" + dateTime +
                ", capacity=" + capacity +
                ", currentAttendees=" + attendees.size() +
                ", averageRating=" + averageRating +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return eventId.equals(event.eventId);
    }

    @Override
    public int hashCode() {
        return eventId.hashCode();
    }
}