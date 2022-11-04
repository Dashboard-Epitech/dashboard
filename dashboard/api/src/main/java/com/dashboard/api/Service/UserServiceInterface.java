package com.dashboard.api.Service;

import java.util.List;

import com.dashboard.api.Entity.Role;
import com.dashboard.api.Entity.User;

public interface UserServiceInterface {
    User saveUser(User user);
    Role saveRole(Role role);
    User getUser(String username);
    List<User> getUsers();
    void setUserRole(String username, String roleName);
}