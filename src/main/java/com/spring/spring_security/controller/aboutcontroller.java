package com.spring.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engineer")
public class aboutcontroller {
    @PostMapping("/Ele")
    public String Elecengineer(){
        return "hi engineer";
    }
    @GetMapping("/name")
    public String name(){
        return "my name osama ";
    }

}
