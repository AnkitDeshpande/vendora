package com.project.vendora.core.entity;

public interface TenantAware {

    Tenant getTenant();

    void setTenant(Tenant tenant);
}