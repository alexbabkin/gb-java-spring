package com.github.alexbabkin.security.services;

import com.github.alexbabkin.security.models.Role;
import com.github.alexbabkin.security.repositories.RoleRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
