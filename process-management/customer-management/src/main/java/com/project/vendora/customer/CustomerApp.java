package com.project.vendora.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.vendora")
public class CustomerApp {
    /**
     * Entry method for application.
     *
     * @param args - The args
     */
    public static void main(String[] args) {
        SpringApplication.run(CustomerApp.class, args);
    }
}
