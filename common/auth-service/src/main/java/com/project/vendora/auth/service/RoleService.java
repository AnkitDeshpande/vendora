package com.project.vendora.auth.service;

import com.project.vendora.core.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    Role getRoleById(Long id);

    List<Role> getAllRoles();

    Role updateRole(Long id, Role role);

    void deleteRole(Long id);
}
