package com.dashboard.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dashboard.api.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
