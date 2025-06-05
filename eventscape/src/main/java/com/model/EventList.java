package com.model;

import java.util.ArrayList;
import java.util.List;

public class EventList {
    private static EventList instance;
    private List<Event> events;

    private EventList() {
        events = DataLoader.getEvents();
        if (events == null) {
            events = new ArrayList<>();
        }
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

    public void removeEvent(Event event) {
        events.remove(event);
    }
}
