package com.ace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Boot2 {

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Boot2.context = SpringApplication.run(Boot2.class, args);
    }
}