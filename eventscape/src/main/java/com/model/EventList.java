package com.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.model.Event.WaitlistUpdateResult;

public class EventList {
    private static EventList instance;
    private List<Event> events;

    private EventList() {
        events = new ArrayList<>();
    }

    public static EventList getInstance() {
        if (instance == null) {
            instance = new EventList();
        }
        return instance;
    }

    /** Add a new event if it's not already in the list */
    public boolean addEvent(Event event) {
        if (event != null && !events.contains(event)) {
            events.add(event);
            return true;
        }
        return false;
    }

    /** Remove an event by its ID */
    public boolean removeEvent(Event event2) {
        return events.remove(event2);
    }

    /** Get an event by its ID */
    public Event getEventById(UUID eventId) {
        for (Event event : events) {
            if (event.getEventId().equals(eventId)) {
                return event;
            }
        }
        return null;
    }

    /** Get all events */
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    /** Get events by name (case-insensitive) */
    public List<Event> getEventsByName(String name) {
        return events.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    /** Get events by date */
    public List<Event> getEventsByDate(LocalDate date) {
        return events.stream()
                .filter(e -> e.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    /** Update the waitlist of a specific event */
    public WaitlistUpdateResult updateWaitlist(UUID eventId) {
        Event event = getEventById(eventId);
        if (event != null) {
            return event.updateWaitlist();
        }
        return null;
    }

    // Removed duplicate removeEvent method to resolve compilation error.
}
