package com.project.vendora.multitenant.datasource;


import com.project.vendora.multitenant.core.TenantContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantAwareRoutingSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenantName();
    }

}
