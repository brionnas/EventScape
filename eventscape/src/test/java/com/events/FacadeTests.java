package com.events; 
import com.model.Facade;
import com.model.User;
import com.model.Event;
import java.util.List;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
public class FacadeTests {

    @Test 
        public void testTesting(){
         assertTrue(true); 
      }

    @Test
    public void testFacadeSingleton() {
        Facade facade1 = Facade.getInstance();
        Facade facade2 = Facade.getInstance();
        assertEquals("Both instances should be the same", facade1, facade2);
    }   

    @Test
    public void testAddUser() {
        Facade facade = Facade.getInstance();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dob = null;
        try {
            dob = sdf.parse("2004-10-19");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        boolean result = facade.addUser("KnightAtlas360", "Roxy", "Sparks", "carletnightowl@email.com", "847-1656", dob, "smolbeans22");
        assertTrue("Add user should return true for valid user", result);
    }

    @Test
    public void testLogin() {
        Facade facade = Facade.getInstance();
        User result = facade.login("KnightAtlas360", "smolbeans22");
        assertFalse("Login should fail for non-existent user", result);
    }

    @Test
    public void testFindUser() {
        Facade facade = Facade.getInstance();
        User result = facade.findUser("KnightAtlas360");
        assertFalse("Find user should return null for non-existent user", result);
    }

    @Test
    public void testGetAllUsers() {
        Facade facade = Facade.getInstance();
        List<User> users = facade.getAllUsers();
        assertFalse("User list should not be empty", users.isEmpty());
    }

    @Test
    public void testRemoveUser() {
        Facade facade = Facade.getInstance();
        boolean result = facade.removeUser("KnightAtlas360");
        assertFalse("Remove user should return false for non-existent user", result);
    }

    @Test
    public void testLogout() {
        Facade facade = Facade.getInstance();
        facade.logout(); // No return value, just checking for exceptions
        // If no exception is thrown, the test passes
    }

    @Test
    public void testAddEvent() {
        Facade facade = Facade.getInstance();
        boolean result = facade.addEvent("Test Event", "2023-10-01", "2023-10-02", "Test Location", "Test Description");
        assertTrue("Add event should return true for valid event", result);
    }
    
    @Test   
    public void testGetEventById() {
        Facade facade = Facade.getInstance();
        Event event = facade.getEventById("Concert127");
        assertFalse("Get event by ID should return null for non-existent event", event);
    }

    @Test   
    public void testRemoveEvent() {
        Facade facade = Facade.getInstance();
        boolean result = facade.removeEvent("ComedyShow12690");
        assertFalse("Remove event should return false for non-existent event", result);
    }

    @Test
    public void testGetAllEvents() {
        Facade facade = Facade.getInstance();
        List<Event> events = facade.getAllEvents();
        assertFalse("Event list should not be empty", events.isEmpty());
    }

    @Test
    public void testSaveData() {
        Facade facade = Facade.getInstance();
        boolean result = facade.saveData();
        assertTrue("Save data should return true", result);
    }

    @Test
    public void testLoadData() {
        Facade facade = Facade.getInstance();
        boolean result = facade.loadData();
        assertTrue("Load data should return true", result);
    }
}
    

    //(^quick todo in class to show corrected^)

    //FACADE TESTS CORRECTIONS
    //-> needs to be in junit format
    //-> one liners
    //-> add event methods



    /*public static void main(String[] args) {
        FacadeTests tester = new FacadeTests();
        tester.runTests();
    }

    public void runTests() {
        System.out.println("===== FACADE TESTER START =====");

        Facade facade = Facade.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Test 1: Add valid user
            System.out.println("\n-- Test 1: Add Valid User --");
            Date dob = sdf.parse("2004-10-19");
            boolean added = facade.addUser("KnightAtlas360", "Roxy", "Sparks", "scarletnightowl@email.com", "847-1656", dob, "smolbeans22");
            System.out.println("Expected: true | Actual: " + added);

            // Test 2: Add duplicate user
            System.out.println("\n-- Test 2: Add Duplicate User --");
            boolean duplicateAdd = facade.addUser("KnightAtlas360", "Roxy", "Sparks", "scarletnightowl@email.com", "847-1656", dob, "smolbeans22");
            System.out.println("Expected: false | Actual: " + duplicateAdd);

            // Test 3: Find user by username
            System.out.println("\n-- Test 3: Find User --");
            User found = facade.findUser("KnightAtlas360");
            System.out.println("Expected: KnightAtlas360 | Actual: " + (found != null ? found.getUserName() : "null"));

            // Test 4: Find non-existent user
            System.out.println("\n-- Test 4: Find Non-existent User --");
            User missing = facade.findUser("ghost");
            System.out.println("Expected: null | Actual: " + (missing != null ? missing.getUserName() : "null"));

            // Test 5: Login with correct credentials
            System.out.println("\n-- Test 5: Login (Correct Credentials) --");
            User loggedIn = facade.login("KnightAtlas360", "smolbeans22");
            System.out.println("Expected: KnightAtlas360 | Actual: " + (loggedIn != null ? loggedIn.getUserName() : "null"));

            // Test 6: Login with incorrect password
            System.out.println("\n-- Test 6: Login (Wrong Password) --");
            User failedLogin = facade.login("KnightAtlas360", "wrongpass");
            System.out.println("Expected: null | Actual: " + (failedLogin != null ? failedLogin.getUserName() : "null"));

            // Test 7: List all users
            System.out.println("\n-- Test 7: Get All Users --");
            List<User> users = facade.getAllUsers();
            System.out.println("Total users: " + users.size());
            for (User u : users) {
                System.out.println(" - " + u.getUserName());
            }

            // Test 8: Remove existing user
            System.out.println("\n-- Test 8: Remove User --");
            boolean removed = facade.removeUser("KnightAtlas360");
            System.out.println("Expected: true | Actual: " + removed);

            // Test 9: Remove user again
            System.out.println("\n-- Test 9: Remove Non-existent User --");
            boolean removedAgain = facade.removeUser("KnightAtlas360");
            System.out.println("Expected: false | Actual: " + removedAgain);

            // Test 10: Logout (no visible effect, but should run)
            System.out.println("\n-- Test 10: Logout --");
            facade.logout();
            System.out.println("Logout called (check for save side-effects if any).");

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }

        System.out.println("\n===== FACADE TESTER COMPLETE =====");
    }
}
*/