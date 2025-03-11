package com.project.vendora.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FlywayConfig {

    @Bean
    public String flywayPatchesLocation() {
        Path projectRoot = Paths.get("").toAbsolutePath();
        Path flywayPatchesPath = projectRoot.resolve("database/flyway-patches/tenant");
        return "filesystem:" + flywayPatchesPath.toString();
    }
}