package com.model;

import java.util.ArrayList;
import java.util.List;

public class EventList {
    private static EventList instance;
    private final List<Event> events;

    private EventList() {
        events = new ArrayList<>();
        // Could load from DataLoader here if implemented
    }

    public static EventList getInstance() {
        if (instance == null) {
            instance = new EventList();
        }
        return instance;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public Event getEventById(String eventId) {
        for (Event event : events) {
            if (event.getEventId().equals(eventId)) {
                return event;
            }
        }
        return null;
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }
}

