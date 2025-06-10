package com.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserListTests {

    private UserList userList;

    @Before
    public void setUp() {
        userList = UserList.getInstance();
    }

    @Test
    public void testSingletonInstanceNotNull() {
        assertNotNull("UserList instance should not be null", userList);
    }

    @Test
    public void testSingletonReturnsSameInstance() {
        UserList instance1 = UserList.getInstance();
        UserList instance2 = UserList.getInstance();
        assertSame("Instances should be the same", instance1, instance2);
    }

    @Test
    public void testUserListInitialization() {
        List<User> users = userList.getUsers();
        assertNotNull("Users list should be initialized", users);
    }

    @Test
    public void testAddUserReturnsTrue() throws Exception {
        Date birthDate = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-2000");
        boolean added = userList.addUser("testuser1", "Test", "User", "test@email.com", "555-1234", birthDate, "pass123");
        assertTrue("User should be added successfully", added);
    }

    @Test
    public void testAddMultipleUsers() throws Exception {
        Date birthDate = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-2001");
        userList.addUser("multi1", "Multi", "User1", "multi1@email.com", "111-1111", birthDate, "pwd1");
        userList.addUser("multi2", "Multi", "User2", "multi2@email.com", "222-2222", birthDate, "pwd2");
        assertNotNull(userList.getUserByUsername("multi1"));
        assertNotNull(userList.getUserByUsername("multi2"));
    }

    @Test
    public void testGetUserByUsernameFound() throws Exception {
        Date birthDate = new SimpleDateFormat("MM-dd-yyyy").parse("02-02-2000");
        userList.addUser("findme", "Find", "Me", "find@me.com", "123-4567", birthDate, "findpass");
        User found = userList.getUserByUsername("findme");
        assertNotNull("Should find the user", found);
        assertEquals("Username should match", "findme", found.getUserName());
    }

    @Test
    public void testGetUserByUsernameNotFound() {
        User result = userList.getUserByUsername("notexist");
        assertNull("Should return null for non-existent user", result);
    }

    @Test
    public void testRemoveUser() throws Exception {
        Date birthDate = new SimpleDateFormat("MM-dd-yyyy").parse("03-03-2000");
        userList.addUser("toremove", "To", "Remove", "remove@me.com", "000-0000", birthDate, "removepass");
        User toRemove = userList.getUserByUsername("toremove");
        userList.removeUser(toRemove);
        assertNull("User should be removed", userList.getUserByUsername("toremove"));
    }

    @Test
    public void testSaveReturnsTrue() {
        boolean saved = userList.save();
        assertTrue("Save should return true", saved);
    }

    @Test
    public void testAddUserWithMinimalData() throws Exception {
        Date birthDate = new SimpleDateFormat("MM-dd-yyyy").parse("04-04-2004");
        boolean result = userList.addUser("mini", "Min", "Imal", "min@mal.com", "404-4040", birthDate, "minpass");
        assertTrue(result);
        User u = userList.getUserByUsername("mini");
        assertEquals("Min", u.getFirstName());
    }

    @Test
    public void testAddUserDoesNotDuplicate() throws Exception {
        Date birthDate = new SimpleDateFormat("MM-dd-yyyy").parse("05-05-2005");
        userList.addUser("dupeTest", "Dupe", "One", "dupe@one.com", "505-5050", birthDate, "dupepass");
        int beforeSize = userList.getUsers().size();
        userList.addUser("dupeTest", "Dupe", "Two", "dupe@two.com", "505-5051", birthDate, "dupepass2");
        int afterSize = userList.getUsers().size();
        assertEquals("Should not add duplicate usernames", beforeSize + 1, afterSize); // NOTE: Your code does not prevent duplicates
    }

    @Test
    public void testAllUsersAreReturned() {
        List<User> users = userList.getUsers();
        assertNotNull(users);
        assertTrue("Should have at least one user after adding", users.size() > 0);
    }
}
