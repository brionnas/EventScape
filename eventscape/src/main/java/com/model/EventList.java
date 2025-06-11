package com.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EventList {
    private static EventList instance;
    private List<Event> events;

    private EventList() {
        // Load from DataLoader (if implemented)
        List<Event> loaded = DataLoader.loadEvents();
        events = (loaded != null) ? loaded : new ArrayList<>();
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

    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    public boolean addEvent(Event event) {
        if (event != null && !events.contains(event)) {
            events.add(event);
            return true;
        }
        return false;
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public Event getEventById(UUID eventId) {
        return events.stream()
                .filter(event -> event.getEventId().equals(eventId))
                .findFirst()
                .orElse(null);
    }

    public List<Event> searchEvents(String keyword) {
        String lower = keyword.toLowerCase();
        return events.stream()
                .filter(event ->
                        event.getName().toLowerCase().contains(lower) ||
                        event.getCategory().toString().toLowerCase().contains(lower) ||
                        event.getSubCategory().toString().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public List<Event> getEventsByCategory(Category category) {
        return events.stream()
                .filter(e -> e.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<Event> getEventsByGenre(Genre genre) {
        return events.stream()
                .filter(e -> e.getSubCategory().equals(genre))
                .collect(Collectors.toList());
    }

    public List<Event> getEventsByDate(LocalDate date) {
        return events.stream()
                .filter(e -> e.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Event> getUpcomingEvents() {
        LocalDateTime now = LocalDateTime.now();
        return events.stream()
                .filter(e -> e.getDateTime().isAfter(now))
                .sorted(Comparator.comparing(Event::getDateTime))
                .collect(Collectors.toList());
    }

    public List<Event> getAvailableEvents() {
        return events.stream()
                .filter(e -> !e.isAtCapacity())
                .collect(Collectors.toList());
    }

    public List<Event> getEventsByRating(float min, float max) {
        return events.stream()
                .filter(e -> e.getAverageRating() >= min && e.getAverageRating() <= max)
                .collect(Collectors.toList());
    }

    public List<Event> getTopRatedEvents(int limit) {
        return events.stream()
                .sorted((e1, e2) -> Float.compare(e2.getAverageRating(), e1.getAverageRating()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Event> getTodaysEvents() {
        return getEventsByDate(LocalDate.now());
    }

    public void save() {
        DataWriter.saveEvents();
    }

    public void clearAllEvents() {
        events.clear();
    }

    public int getEventCount() {
        return events.size();
    }
}
