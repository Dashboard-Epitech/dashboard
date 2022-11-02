package com.dashboard.api.Repository;

import org.springframework.data.repository.CrudRepository;
import com.dashboard.api.Entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}
