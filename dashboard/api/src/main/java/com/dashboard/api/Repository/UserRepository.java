package com.dashboard.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.dashboard.api.Entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();
    public User findByEmail(String email);
}
