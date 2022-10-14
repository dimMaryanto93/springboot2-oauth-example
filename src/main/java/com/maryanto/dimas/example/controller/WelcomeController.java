package com.maryanto.dimas.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/index")
public class WelcomeController {

    @GetMapping
    public String index() {
        return "index";
    }
}
