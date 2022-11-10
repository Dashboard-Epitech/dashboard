package com.dashboard.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dashboard.api.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}
