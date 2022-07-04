package com.github.alexbabkin.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @GetMapping("/add")
    public String addPost() {
        return "Add post successful";
    }

    @GetMapping("/delete")
    public String deletePost() {
        return "Delete post successful";
    }

    @GetMapping("/edit")
    public String editPost() {
        return "Edit post successful";
    }
}
