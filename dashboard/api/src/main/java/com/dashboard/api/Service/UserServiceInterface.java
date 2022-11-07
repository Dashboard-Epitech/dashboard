package com.dashboard.api.Service;

import java.util.List;
import java.util.Optional;

import com.dashboard.api.Entity.Role;
import com.dashboard.api.Entity.User;
import com.dashboard.api.Exception.NoDataFoundException;
import com.dashboard.api.Exception.UserNotFoundException;

public interface UserServiceInterface {
    Role saveRole(Role role);
    void setUserRole(String username, String roleName);

    User getUser(Long id) throws UserNotFoundException;
    User getUserByEmail(String username) throws UserNotFoundException;
    Iterable<User> getUsers() throws NoDataFoundException;

    User editUser(Long id, User user) throws UserNotFoundException;
    void removeUser(Long id) throws UserNotFoundException;
}