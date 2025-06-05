package com.model;

import java.util.List;
import java.util.UUID;

public class EventFacade {
    private static EventFacade instance;
    private EventList eventList;

    private EventFacade() {
        eventList = EventList.getInstance();
    }

    public static EventFacade getInstance() {
        if (instance == null) {
            instance = new EventFacade();
        }
        return instance;
    }

    public List<Event> getAllEvents() {
        return eventList.getAllEvents();
    }

    public Event findEventById(UUID id) {
        for (Event e : eventList.getAllEvents()) {
            if (e.getEventId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public void addEvent(Event event) {
        eventList.addEvent(event);
    }

    public void removeEvent(Event event) {
        eventList.removeEvent(event);
    }
}
