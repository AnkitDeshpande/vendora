package com.project.vendora.admin.service;

import com.project.vendora.core.entity.Tenant;
import com.project.vendora.multitenant.repository.TenantRepository;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantInfoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment env;

    public void createTenant(String tenantId) {
        String dbName = "shop_" + tenantId;
        String dbUser = "user_" + tenantId;
        String dbPassword = env.getProperty("multitenancy.db.password", "password");
        String dbHost = env.getProperty("multitenancy.db.host", "localhost");
        String dbPort = env.getProperty("multitenancy.db.port", "5432");

        String dbUrl = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);

        createDatabase(dbName, dbUser);

        Tenant tenantInfo = new Tenant(tenantId, dbName, dbUrl, dbUser, dbPassword);
        tenantInfoRepository.save(tenantInfo);

        // Run Flyway migrations
        runFlywayMigrations(dbUrl, dbUser, dbPassword);
    }

    private void createDatabase(String dbName, String dbUser) {
        jdbcTemplate.execute(String.format("CREATE DATABASE %s OWNER %s;", dbName, dbUser));
    }

    private void runFlywayMigrations(String dbUrl, String dbUser, String dbPassword) {
        Flyway flyway = Flyway.configure()
                .dataSource(dbUrl, dbUser, dbPassword)
                .locations("filesystem:/home/agsuser/Downloads/Vendora/database/flyway/patches/tenant")
                .loggers("slf4j")
                .load();
        flyway.migrate();

    }
}
