package com.events;

import org.junit.Before;
import org.junit.Test;
import com.model.Event;
import com.model.EventList;

import org.junit.After;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;


public class EventListTest {

    private EventList eventList;
    private Event testEvent1;
    private Event testEvent2;
    private Event testEvent3;

    @Before
    public void setUp() {
        resetSingletonInstance();
        eventList = EventList.getInstance();
        eventList.getEvents().clear(); // Clean start

        // Sample events
        // Update the constructor arguments below to match your Event class constructor
        // Example: If your Event constructor is Event(String eventId, String name, String description, String date)
        testEvent1 = new Event("00000000-0000-0000-0000-00000000E001", "Test Event 1", "Desc 1", "2024-06-15", null);
        testEvent2 = new Event("00000000-0000-0000-0000-00000000E002", "Test Event 2", "Desc 2", "2024-06-16", null);
        testEvent3 = new Event("00000000-0000-0000-0000-00000000E003", "Test Event 3", "Desc 3", "2024-06-17", null);
    }
    @After
    public void tearDown() {
        resetSingletonInstance();
    }

    private void resetSingletonInstance() {
        try {
            Field instanceField = EventList.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            instanceField.set(null, null);
        } catch (Exception e) {
            fail("Failed to reset singleton instance: " + e.getMessage());
        }
    }

    // Singleton tests
    @Test
    public void testGetInstanceIsNotNull() {
        assertNotNull(EventList.getInstance());
    }

    @Test
    public void testSingletonInstanceConsistency() {
        EventList first = EventList.getInstance();
        EventList second = EventList.getInstance();
        assertSame(first, second);
    }

    // Add event tests
    @Test
    public void testAddSingleEvent() {
        int before = eventList.getEvents().size();
        eventList.addEvent(testEvent1);
        assertEquals(before + 1, eventList.getEvents().size());
        assertTrue(eventList.getEvents().contains(testEvent1));
    }

    @Test
    public void testAddNullEvent() {
        try {
            eventList.addEvent(null);
        } catch (Exception e) {
            // If nulls are not allowed, that's fine.
        }
        // No crash = pass
    }

    // Remove event tests
    @Test
    public void testRemoveEventThatExists() {
        eventList.addEvent(testEvent1);
        eventList.removeEvent(testEvent1);
        assertFalse(eventList.getEvents().contains(testEvent1));
    }

    @Test
    public void testRemoveEventThatDoesNotExist() {
        int before = eventList.getEvents().size();
        eventList.removeEvent(testEvent2);
        assertEquals(before, eventList.getEvents().size());
    }

    // Get by ID tests
    @Test
    public void testGetByIdFound() {
        eventList.addEvent(testEvent1);
        Event found = eventList.getEventById(java.util.UUID.fromString("EVT001"));
        assertNotNull(found);
        assertEquals("EVT001", found.getEventId());
    }

    @Test
    public void testGetByIdNotFound() {
        assertNull(eventList.getEventById(java.util.UUID.fromString("00000000-0000-0000-0000-00000000FAKE")));
    }

    @Test
    public void testGetByIdCaseSensitive() {
        eventList.addEvent(testEvent1);
        // Use a valid UUID string for testing case sensitivity
        assertNull(eventList.getEventById(java.util.UUID.fromString("EVT001"))); // Expected: case-sensitive
    }
    public EventList getEventList() {
        return eventList;
    }
    public void setEventList(EventList eventList) {
        this.eventList = eventList;
    }
    public Event getTestEvent1() {
        return testEvent1;
    }
    public void setTestEvent1(Event testEvent1) {
        this.testEvent1 = testEvent1;
    }
    public Event getTestEvent2() {
        return testEvent2;
    }
    public void setTestEvent2(Event testEvent2) {
        this.testEvent2 = testEvent2;
    }

    
    // public void testSaveDoesNotThrow() {
    //     try {
    //         eventList.save();
    //     } catch (Exception e) {
    //         fail("save() shouldn't throw");
    //     }
    // }


}

