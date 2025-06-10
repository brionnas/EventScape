package com.model; 

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue; 

import org.junit.Test; 
import com.model.Facade; 


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FacadeTests {

    public static void main(String[] args) {
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
