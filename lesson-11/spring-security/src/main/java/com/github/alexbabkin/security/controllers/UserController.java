package com.github.alexbabkin.security.controllers;

import com.github.alexbabkin.security.models.User;
import com.github.alexbabkin.security.services.UserService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/add")
    public String addUser() {
        return "Add user successful";
    }

    @GetMapping("/delete")
    public String deleteUser() {
        return "Delete user successful";
    }

    @GetMapping("/edit")
    public String editUser() {
        return "Edit user successful";
    }

    @GetMapping("/show")
    public User showUserInfo(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }
}
