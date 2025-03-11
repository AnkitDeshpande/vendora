package com.project.vendora.multitenant.repository;

import com.project.vendora.core.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, String> {
    Optional<Tenant> findByTenantId(String tenantId);
}