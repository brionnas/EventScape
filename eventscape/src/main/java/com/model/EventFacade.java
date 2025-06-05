package com.model;

import java.util.List;

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
        return eventList.getEvents();
    }

    public void addEvent(Event event) {
        eventList.addEvent(event);
    }

    public Event getEventById(String eventId) {
        return eventList.getEventById(eventId);
    }

    public boolean removeEvent(String eventId) {
        Event event = getEventById(eventId);
        if (event != null) {
            eventList.removeEvent(event);
            return true;
        }
        return false;
    }
}
