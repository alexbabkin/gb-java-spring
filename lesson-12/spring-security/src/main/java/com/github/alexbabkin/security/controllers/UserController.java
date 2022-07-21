package com.github.alexbabkin.security.controllers;

import com.github.alexbabkin.security.exceptions.DataValidationException;
import com.github.alexbabkin.security.models.User;
import com.github.alexbabkin.security.services.UserService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.toList()));
        }
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            throw new DataValidationException("A user with this username exists");
        }
        return userService.save(user);
    }
}
