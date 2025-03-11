package com.project.vendora.multitenant.datasource;

import com.project.vendora.core.entity.Tenant;
import com.project.vendora.core.entity.TenantAware;
import com.project.vendora.multitenant.core.TenantContext;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class TenantListener {

    @PreUpdate
    @PreRemove
    @PrePersist
    public void setTenant(TenantAware entity) {
        Tenant tenant = entity.getTenant();

        if(tenant == null) {
            tenant = new Tenant();

            entity.setTenant(tenant);
        }

        final String tenantName = TenantContext.getTenantName();

        tenant.setDbName(tenantName);
    }
}