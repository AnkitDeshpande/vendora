package com.project.vendora.multitenant.config;


import com.project.vendora.multitenant.request.TenantConfiguration;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@ConfigurationProperties(prefix = "application")
public class ApplicationConfiguration {

    private final List<TenantConfiguration> tenants;

    public ApplicationConfiguration(List<TenantConfiguration> tenants) {
        this.tenants = tenants;
    }

}
