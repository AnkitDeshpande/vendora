package com.project.vendora.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableJpaAuditing
@EnableTransactionManagement
@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = {"com.project.vendora.*"})
@EntityScan(basePackages = "com.project.vendora.core.entity")
@EnableJpaRepositories(basePackages = {"com.project.vendora.*"})
@ComponentScan(basePackages = {"com.project.vendora.*"})
public class NotificationApp {
    /**
     * Entry method for application.
     *
     * @param args - The args
     */
    public static void main(String[] args) {
        SpringApplication.run(NotificationApp.class, args);
    }
}