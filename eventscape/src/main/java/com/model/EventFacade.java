package com.model;

import java.util.List;

public class EventFacade {
    private static EventFacade instance;
    private final EventList eventList;

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
        return eventList.getEvents();
    }

    public Event findEventById(String eventId) {
        return eventList.getEventById(eventId);
    }

    public void addEvent(Event event) {
        eventList.addEvent(event);
    }

    public void removeEvent(Event event) {
        eventList.removeEvent(event);
    }
}
