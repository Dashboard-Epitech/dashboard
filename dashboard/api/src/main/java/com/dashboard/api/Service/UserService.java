package com.dashboard.api.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dashboard.api.Entity.Role;
import com.dashboard.api.Entity.User;
import com.dashboard.api.Exception.NoDataFoundException;
import com.dashboard.api.Exception.UserNotFoundException;
import com.dashboard.api.Repository.RoleRepository;
import com.dashboard.api.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @Override
    public List<User> getUsers() throws NoDataFoundException {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }

        return users;
    }

    @Override
    public void removeUser(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        userRepository.delete(user.get());
    }

    @Override
    public User editUser(Long id, User userFormData) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        user.get()
            .setUsername(userFormData.getUsername())
            .setFirstName(userFormData.getFirstName())
            .setLastName(userFormData.getLastName());
        
        return userRepository.save(user.get());
    }
    
    public User registerUser(User user) throws ConstraintViolationException {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.addRole(roleRepository.findByName("USER"));
            return userRepository.save(user);
        } catch(Exception validationEx) {
            throw validationEx;
        }
    }

    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void setUserRole(String email, String roleName) {
        User user = userRepository.findByEmail(email);    
        Role role = roleRepository.findByName(roleName);
        
        user.getRoles().add(role);
    }
}
