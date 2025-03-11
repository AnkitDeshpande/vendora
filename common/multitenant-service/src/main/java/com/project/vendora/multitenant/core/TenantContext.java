package com.project.vendora.multitenant.core;

public class TenantContext {

    private static final ThreadLocal<String> tenant = new ThreadLocal<>();

    public static void setTenantName(String tenantName) {
        tenant.set(tenantName);
    }

    public static String getTenantName() {
        return tenant.get();
    }

    public static void clear() {
        tenant.remove();
    }

}
