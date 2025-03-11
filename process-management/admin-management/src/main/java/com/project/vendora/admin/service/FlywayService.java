package com.project.vendora.admin.service;


import com.project.vendora.core.entity.Tenant;

public interface FlywayService {
    /**
     * Creates a new tenant database and initializes its schema.
     *
     * @param tenantId The unique identifier for the tenant.
     */
    void createTenant(String tenantId);

    /**
     * Executes Flyway migrations for an existing tenant.
     *
     * @param tenantId The unique identifier for the tenant.
     */
    void migrateTenant(Tenant tenantId);

    /**
     * Deletes a tenant database.
     *
     * @param tenantId The unique identifier for the tenant.
     */
    void deleteTenant(String tenantId);
}