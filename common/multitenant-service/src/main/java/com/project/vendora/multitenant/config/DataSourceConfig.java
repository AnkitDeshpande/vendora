package com.project.vendora.multitenant.config;

import com.project.vendora.multitenant.datasource.TenantAwareRoutingSource;
import com.project.vendora.multitenant.request.TenantConfiguration;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceConfig {
    @Bean
    public DataSource dataSource(ApplicationConfiguration applicationConfiguration) {

        AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();

        Map<Object,Object> targetDataSources = new HashMap<>();

        for(var tenantConfiguration : applicationConfiguration.getTenants()) {
            // Builds the DataSource for the Tenant
            var tenantDataSource = buildDataSource(tenantConfiguration);
            // Puts it into the DataSources available for routing a Request
            targetDataSources.put(tenantConfiguration.name(), tenantDataSource);
        }

        dataSource.setTargetDataSources(targetDataSources);

        dataSource.afterPropertiesSet();

        return dataSource;
    }

    public DataSource buildDataSource(TenantConfiguration tenantConfiguration) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setInitializationFailTimeout(0);
        dataSource.setMaximumPoolSize(5);
        dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        dataSource.addDataSourceProperty("url", tenantConfiguration.dbUrl());
        dataSource.addDataSourceProperty("user", tenantConfiguration.dbUser());
        dataSource.addDataSourceProperty("password", tenantConfiguration.dbPassword());

        return dataSource;
    }
}
