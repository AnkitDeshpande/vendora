package com.project.vendora.multitenant.async;

import com.project.vendora.multitenant.core.TenantContext;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.NonNull;

public class TenantAwareTaskDecorator implements TaskDecorator {

    @Override
    @NonNull
    public Runnable decorate(@NonNull Runnable runnable) {
        String tenantName = TenantContext.getTenantName();
        return () -> {
            try {
                TenantContext.setTenantName(tenantName);
                runnable.run();
            } finally {
                TenantContext.setTenantName(null);
            }
        };
    }
}