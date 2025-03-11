package com.project.vendora.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tenants", uniqueConstraints = @UniqueConstraint(columnNames = "tenant_id"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tenant extends AuditCommonBaseModel {

    @Column(name = "tenant_id", nullable = false, unique = true)
    private String tenantId;

    @Column(name = "db_name", nullable = false)
    private String dbName;

    @Column(name = "db_url", nullable = false)
    private String dbUrl;

    @Column(name = "db_user", nullable = false)
    private String dbUser;

    @Column(name = "db_password", nullable = false)
    private String dbPassword;
}