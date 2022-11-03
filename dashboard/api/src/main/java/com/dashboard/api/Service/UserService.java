package com.dashboard.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dashboard.api.Entity.Role;
import com.dashboard.api.Entity.User;
import com.dashboard.api.Repository.RoleRepository;
import com.dashboard.api.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserServiceInterface {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void setUserRole(String email, String roleName) {
        User user = userRepository.findByEmail(email);    
        Role role = roleRepository.findByName(roleName);

        user.getRoles().add(role);
    }
}
