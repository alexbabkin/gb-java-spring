package com.github.alexbabkin.security.controllers;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("")
    public String showAdminPanel(Principal principal) {
        return "Welcome to admin panel, " + principal.getName();
    }
}
