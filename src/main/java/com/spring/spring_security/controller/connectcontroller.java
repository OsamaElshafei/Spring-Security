package com.spring.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connect")
public class connectcontroller {
    @GetMapping("/start")
    public String Elecengineer(){
        return "show connectcontroller";
    }
}
