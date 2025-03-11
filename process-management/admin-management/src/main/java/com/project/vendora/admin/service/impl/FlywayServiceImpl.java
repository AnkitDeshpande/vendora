package com.project.vendora.admin.service.impl;

import com.project.vendora.admin.service.FlywayService;
import com.project.vendora.core.entity.Tenant;
import com.project.vendora.multitenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FlywayServiceImpl implements FlywayService {

    private final JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.base.url}")
    private String baseDbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    private final String flywayPatchesLocation;

    private final TenantRepository tenantRepository;

    @Override
    public void createTenant(String tenantId) {
        String dbName = "store_" + tenantId;
        String dbUrl = baseDbUrl + dbName;

        // 1. Create a new database
        jdbcTemplate.execute("CREATE DATABASE " + dbName);

        // 2. Add tenant to the tenants table
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 3. Save the tenant entity using JPA
        Tenant tenant = new Tenant();
        tenant.setTenantId(tenantId);
        tenant.setDbName(dbName);
        tenant.setDbUrl(dbUrl);
        tenant.setDbUser(dbUsername);
        tenant.setDbPassword(dbPassword);

        Tenant savedTenant = tenantRepository.save(tenant);

        // 3. Initialize schema using Flyway
        migrateTenant(savedTenant);
    }

    @Override
    @Transactional
    public void migrateTenant(Tenant tenant) {
        // Fetch tenant database details
        Tenant savedTenant = tenantRepository.findByTenantId(tenant.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));

        String dbUrl = tenant.getDbUrl();
        String dbUsername = tenant.getDbUser();
        String dbPassword = tenant.getDbPassword();

        // Apply Flyway migrations
        migrate(dbUrl, dbUsername, dbPassword);
    }

    @Override
    @Transactional
    public void deleteTenant(String tenantId) {
        /*Fetch tenant database details*/
        Tenant savedTenant = tenantRepository.findByTenantId(tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));

        String dbName = savedTenant.getDbName();

        /* Drop the database */
        jdbcTemplate.execute("DROP DATABASE " + dbName);

        /* Remove tenant from the tenants table */
        jdbcTemplate.update("DELETE FROM tenants WHERE tenant_id = ?", tenantId);
    }

    private void initializeSchema(String dbUrl, String dbUsername, String dbPassword) {
        Flyway flyway = Flyway.configure()
                .dataSource(dbUrl, dbUsername, dbPassword)
                .locations(flywayPatchesLocation)
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }

    private void migrate(String dbUrl, String dbUsername, String dbPassword) {
        Flyway flyway = Flyway.configure()
                .dataSource(dbUrl, dbUsername, dbPassword)
                .locations(flywayPatchesLocation)
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}