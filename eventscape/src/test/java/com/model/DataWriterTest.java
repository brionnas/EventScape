package com.model; 

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue; 


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
    // Simple email validation test
    String email = "test@example.com";
    assertTrue(email.contains("@"));
    assertTrue(email.contains("."));
    assertTrue(email.indexOf("@") > 0);
    assertTrue(email.indexOf("@") < email.lastIndexOf("."));
    }
}
