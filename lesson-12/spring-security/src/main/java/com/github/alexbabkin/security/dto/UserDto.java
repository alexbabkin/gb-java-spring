package com.github.alexbabkin.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotNull(message = "The username must be not empty")
    @Length(min = 3, max = 255, message = "The length of the username should be 3-255 characters")
    private String username;

    @NotNull(message = "The password must be not empty")
    @Length(min = 8, max = 255, message = "The length of the password should be min 8 characters")
    private String password;

    @Email(message = "Please provide a valid email address")
    private String email;
}
