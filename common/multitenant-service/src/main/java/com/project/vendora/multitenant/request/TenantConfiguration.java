package com.project.vendora.multitenant.request;

public record TenantConfiguration(String name, String dbUrl, String dbUser, String dbPassword) {
}
