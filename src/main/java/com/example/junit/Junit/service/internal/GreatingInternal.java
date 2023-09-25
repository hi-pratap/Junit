package com.example.junit.Junit.service.internal;

import com.example.junit.Junit.service.Greating;

public class GreatingInternal implements Greating {
    @Override
    public String hello(String message) {
        if(message==null || message.length()==0) throw new IllegalArgumentException();
        return "Hello "+message;
    }
}
