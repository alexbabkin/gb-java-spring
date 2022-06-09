package com.github.alexbabkin.hiber2;

import com.github.alexbabkin.hiber2.db.SessionFactoryWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hiber2Application {

    public static void main(String[] args) {
        SpringApplication.run(Hiber2Application.class, args);
    }
}
