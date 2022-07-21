package com.github.alexbabkin.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MainController {
    @GetMapping("")
    public String welcome() {
        return "Index page";
    }
}
