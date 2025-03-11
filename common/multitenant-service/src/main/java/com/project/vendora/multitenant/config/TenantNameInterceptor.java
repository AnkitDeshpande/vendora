package com.project.vendora.multitenant.config;


import com.project.vendora.core.constant.Database;
import com.project.vendora.core.constant.Headers;
import com.project.vendora.multitenant.core.TenantContext;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class TenantNameInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

         /*Implement your logic to extract the Tenant Name here. Another way would be to
         parse a JWT and extract the Tenant Name from the Claims in the Token. In the
         example code we are just extracting a Header value:*/
        String tenantName = request.getHeader(Headers.TENANT_ID.name());

        if (tenantName == null || tenantName.isEmpty()) {
            // Set the default tenant to "vendora_master"
            tenantName = Database.DEFAULT_DATABASE.getDbName();
        }

        /* Always set the Tenant Name, so we avoid leaking Tenants between Threads even in the scenario, when no
         Tenant is given. I do this because if somehow the afterCompletion Handler isn't called the Tenant Name
         could still be persisted within the ThreadLocal:*/
        TenantContext.setTenantName(tenantName);

        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {

        // After completing the request, make sure to erase the Tenant from the current Thread. It's
        // because Spring may reuse the Thread in the Thread Pool and you don't want to leak this
        // information:
        TenantContext.setTenantName(null);
    }

}