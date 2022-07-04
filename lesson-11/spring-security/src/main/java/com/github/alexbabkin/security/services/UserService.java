package com.github.alexbabkin.security.services;

import com.github.alexbabkin.security.models.Privilege;
import com.github.alexbabkin.security.models.Role;
import com.github.alexbabkin.security.models.User;
import com.github.alexbabkin.security.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(
                                () ->
                                        new UsernameNotFoundException(
                                                "User " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getUserAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getUserAuthorities(Collection<Role> roles) {
        return getRolesAndPrivileges(roles)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    private List<String> getRolesAndPrivileges(Collection<Role> roles) {
        List<String> result = new ArrayList<>();
        for (Role role : roles) {
            result.add(role.getName());
            result.addAll(
                    role.getPrivileges()
                            .stream()
                            .map(Privilege::getName)
                            .collect(Collectors.toList()));
        }
        return result;
    }

    public User getUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + username + " not found"));
    }
}
