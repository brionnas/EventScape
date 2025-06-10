package com.model; 

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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
    assertTrue(username.length() >= 6); 
    // assuming minimum 6 characters
    String longerUsername = "validusername123";
    assertTrue(longerUsername.length() >= 6);
}

    @Test
    public void testUsernameLengthInvalid() {
    String shortUsername = "bob";
    assertFalse(shortUsername.length() >= 6); 
    // should fail if minimum is 6
    String tooShort = "ab";
    assertFalse(tooShort.length() >= 6);
    }


    @Test
    public void testUsernameNotDuplicate() {
    // Assuming you have a way to get existing usernames
    List<String> existingUsernames = Arrays.asList("john123", "mary456", "bob789");
    // should be unique
    String newUsername = "alice123";
    assertFalse(existingUsernames.contains(newUsername)); 
    // should be duplicate
    String duplicateUsername = "john123";
    assertTrue(existingUsernames.contains(duplicateUsername)); 
    }

    @Test
    public void testStrongPassword() {
    String password = "MyStr0ngP@ssword";
    assertTrue(password.length() >= 8);
    assertTrue(password.matches(".*[A-Z].*")); // has uppercase
    assertTrue(password.matches(".*[a-z].*")); // has lowercase  
    assertTrue(password.matches(".*[0-9].*")); // has number
    }


    @Test
    public void testweakPassword() {
        //short case 
        String shortPassword = "123"; 
        assertFalse(shortPassword.length() >= 8);

        //weak but long enough 
        String commonWeak = "password"; 
        assertTrue(commonWeak.length() >= 8); 
    }

    @Test
    public void testInvalidStudentIdLength() {
        String tooshort = "213"; 
        assertFalse(tooshort.length() >= 7);

        String tooLong = "T2343453453"; 
        assertFalse(tooLong.length() >= 7);

    }
    
}

