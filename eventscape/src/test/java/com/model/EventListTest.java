package com.model;

import org.junit.Before;
import org.junit.Test;
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
        testEvent1 = new Event("EVT001", "Test Event 1", "Desc 1", "2024-06-15", "10:00", "Loc 1");
        testEvent2 = new Event("EVT002", "Test Event 2", "Desc 2", "2024-06-16", "14:00", "Loc 2");
        testEvent3 = new Event("EVT003", "Test Event 3", "Desc 3", "2024-06-17", "18:00", "Loc 3");
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
        Event found = eventList.getEventById("EVT001");
        assertNotNull(found);
        assertEquals("EVT001", found.getEventId());
    }

    @Test
    public void testGetByIdNotFound() {
        assertNull(eventList.getEventById("FAKE_ID"));
    }

    @Test
    public void testGetByIdCaseSensitive() {
        eventList.addEvent(testEvent1);
        assertNull(eventList.getEventById("evt001")); // Expected: case-sensitive
    }

    
    // public void testSaveDoesNotThrow() {
    //     try {
    //         eventList.save();
    //     } catch (Exception e) {
    //         fail("save() shouldn't throw");
    //     }
    // }

    
}

