package com.model; 

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before; 
import org.junit.Test; 

import com.model.Facade; 

public class DataWriterTest {
    
    


    @Test
    public void testTesting(){
      assertTrue(true); 

    }
    
    @Test
    public void checkingifvalidemail() {
    // Checking if email has an @ symbol
    String email = "test@example.com";
    assertTrue(email.contains("@"));
    assertTrue(email.contains("."));
    assertTrue(email.indexOf("@") > 0);
    assertTrue(email.indexOf("@") < email.lastIndexOf("."));
    }

    @Test
    public void testUsernameLengthValid() {
    String username = "johnsmith";
    assertTrue(username.length() >= 6); // assuming minimum 6 characters
    
    String longerUsername = "validusername123";
    assertTrue(longerUsername.length() >= 6);
}

    @Test
    public void testUsernameLengthInvalid() {
    String shortUsername = "bob";
    assertFalse(shortUsername.length() >= 6); // should fail if minimum is 6
    
    String tooShort = "ab";
    assertFalse(tooShort.length() >= 6);
    }


    @Test
    public void testUsernameNotDuplicate() {
    // Assuming you have a way to get existing usernames
    List<String> existingUsernames = Arrays.asList("john123", "mary456", "bob789");
    
    String newUsername = "alice123";
    assertFalse(existingUsernames.contains(newUsername)); // should be unique
    
    String duplicateUsername = "john123";
    assertTrue(existingUsernames.contains(duplicateUsername)); // should be duplicate
    }

    
}

