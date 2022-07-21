package com.github.alexbabkin.security.configs;

import com.github.alexbabkin.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/show")
                .authenticated()
                .antMatchers("/user/add")
                .hasAnyAuthority("ADD_USER", "EDIT_USER", "DELETE_USER")
                .antMatchers("/user/edit")
                .hasAnyAuthority("EDIT_USER", "DELETE_USER")
                .antMatchers("/user/delete")
                .hasAuthority("DELETE_USER")
                .antMatchers("/post/add")
                .hasAnyAuthority("ADD_POST", "EDIT_POST", "DELETE_POST")
                .antMatchers("/post/edit")
                .hasAnyAuthority("EDIT_POST", "DELETE_POST")
                .antMatchers("/post/delete")
                .hasAuthority("DELETE_POST")
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
