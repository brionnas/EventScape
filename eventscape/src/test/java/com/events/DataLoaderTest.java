package com.events;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import org.junit.Test;

import com.model.DataLoader;
import com.model.Event;
import com.model.Facade;
import com.model.User; 

public class DataLoaderTest {
   
    @Test
    public void testTesting(){
      assertTrue(true); 
    }

    @Test
    public void testingGettingUsers() {
        
        ArrayList<User> users = DataLoader.getUsers();
        assertNotNull("Users list should not be null", users);
        assertFalse("Users list should not be empty", users.isEmpty());
        if (!users.isEmpty()) {
            User firstUser = users.get(0);
            assertNotNull("First user should not be null", firstUser);
            assertNotNull("User name should not be null", firstUser.getUserName());
            assertNotNull("User email should not be null", firstUser.getEmail());
            assertTrue("User name should not be empty", 
                firstUser.getUserName().length() > 0);
            assertTrue("Email should contain @", 
                firstUser.getEmail().contains("@"));
        }

    }

    @Test
    public void testLoadingEvents() { 
        ArrayList<Event> events = DataLoader.loadEvents(); 
        assertNotNull("Events list should not be null", events);
        if (!events.isEmpty()) {
            Event firstEvent = events.get(0); 
            assertNotNull("First event should not be null", firstEvent); 
            assertNotNull("Event ID should not be null", firstEvent.getEventId());
            assertNotNull("Event name should not be null", firstEvent.getName()); 
        }
    }

    @Test
    public void testingGetEventsThrowsException() {
        try {
            DataLoader.loadEvents();
            fail("Expected UnsupportedOperationException to be thrown"); 
        } catch (UnsupportedOperationException e) { 
            assertEquals("Expected specific message", "Unimplemented method 'getEvents'", e.getMessage()); 
        }
     }

}
