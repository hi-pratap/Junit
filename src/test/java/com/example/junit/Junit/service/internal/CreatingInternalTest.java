package com.example.junit.Junit.service.internal;

import com.example.junit.Junit.service.Greating;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatingInternalTest {
  private  Greating greating=null;
    @BeforeEach
    void setup(){
        System.out.println("setup");
        greating=new GreatingInternal();
    }
    @Test
    void hello(){
        String junit = greating.hello("Junit");
        assertNotNull(junit);
      assertEquals("Hello Junit",junit);
    }

    @Test( )
    void helloShouldThrowException_For_MessageIsNull(){
        System.out.println("helloShouldThrowException_For_MessageIsNull");
     assertThrows(IllegalArgumentException.class,()-> greating.hello(null));
    }

    @Test( )
    void helloShouldThrowException_For_MessageIsBlank(){
        System.out.println("helloShouldThrowException_For_MessageIsBlank");
     assertThrows(IllegalArgumentException.class,()-> greating.hello(""));
    }

    @AfterEach
    void tearDown(){
        System.out.println("TearDown");
        greating=null;
    }
}