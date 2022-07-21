package com.github.alexbabkin.security.models;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "The username must be not empty")
    @Length(min = 3, max = 255, message = "The length of the username should be 3-255 characters")
    @Column(name = "username")
    private String username;

    @NotNull(message = "The password must be not empty")
    @Length(min = 8, max = 255, message = "The length of the password should be min 8 characters")
    @Column(name = "password")
    private String password;

    @NotNull(message = "The email must be not empty")
    @Email(regexp = ".+[@].+[\\.].+", message = "Please provide a valid email address")
    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
