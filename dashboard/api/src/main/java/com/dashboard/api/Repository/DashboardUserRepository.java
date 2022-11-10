package com.dashboard.api.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dashboard.api.Entity.DashboardUser;

public interface DashboardUserRepository extends CrudRepository<DashboardUser, Long> {
    public List<DashboardUser> findAll();
    public Optional<DashboardUser> findByEmail(String email);
    public Optional<DashboardUser> findByUsername(String username);
}
