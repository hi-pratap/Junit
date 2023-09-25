package com.example.junit.Junit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class Hello {

    @GetMapping(value = "/hello")
    public String getHello(){
        return "Hello Coder!!";
    }

}
