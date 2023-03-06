package com.spring.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foot")
public class footballcontroller {
    @PostMapping("/start")
    public String Elecengineer(){
        return "show football";
    }

}
