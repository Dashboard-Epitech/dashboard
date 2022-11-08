package com.dashboard.api.Service;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Role;
import com.dashboard.api.Entity.DashboardUser;
import com.dashboard.api.Exception.NoDataFoundException;
import com.dashboard.api.Exception.UserNotFoundException;
import com.dashboard.api.Repository.RoleRepository;
import com.dashboard.api.Repository.DashboardUserRepository;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MailService mailService;
    private final DashboardUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DashboardUser getUser(Long id) throws UserNotFoundException {
        Optional<DashboardUser> user = userRepository.findById(id);
=======
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
>>>>>>> f74fb6358978fd1ef1132795bb0f7f54b6df8ee4
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }

<<<<<<< HEAD
    public DashboardUser getUserByEmail(String email) throws UserNotFoundException {
        DashboardUser user = userRepository.findByEmail(email).get();
=======
    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
>>>>>>> f74fb6358978fd1ef1132795bb0f7f54b6df8ee4
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

<<<<<<< HEAD
    public List<DashboardUser> getUsers() throws NoDataFoundException {
        List<DashboardUser> users = userRepository.findAll();
=======
    @Override
    public List<User> getUsers() throws NoDataFoundException {
        List<User> users = userRepository.findAll();
>>>>>>> f74fb6358978fd1ef1132795bb0f7f54b6df8ee4

        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }

        return users;
    }

<<<<<<< HEAD
    public void removeUser(Long id) throws UserNotFoundException {
        Optional<DashboardUser> user = userRepository.findById(id);
=======
    @Override
    public void removeUser(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
>>>>>>> f74fb6358978fd1ef1132795bb0f7f54b6df8ee4
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        userRepository.delete(user.get());
    }

<<<<<<< HEAD
    public DashboardUser editUser(Long id, DashboardUser userFormData) throws UserNotFoundException {
        Optional<DashboardUser> user = userRepository.findById(id);
=======
    @Override
    public User editUser(Long id, User userFormData) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
>>>>>>> f74fb6358978fd1ef1132795bb0f7f54b6df8ee4
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        user.get()
            .setUsername(userFormData.getUsername())
<<<<<<< HEAD
            .setEmail(userFormData.getEmail())
            .setPassword(userFormData.getPassword());
=======
            .setFirstName(userFormData.getFirstName())
            .setLastName(userFormData.getLastName());
>>>>>>> f74fb6358978fd1ef1132795bb0f7f54b6df8ee4
        
        return userRepository.save(user.get());
    }
    
<<<<<<< HEAD
    public DashboardUser registerUser(DashboardUser user) throws Exception {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.addRole(roleRepository.findByName("USER"));

            String verificationCode = RandomString.make(64);
            user.setVerificationCode(verificationCode);

            userRepository.save(user);

            mailService.sendVerificationMail(user, verificationCode);

            return user;
=======
    public User registerUser(User user) throws ConstraintViolationException {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.addRole(roleRepository.findByName("USER"));
            return userRepository.save(user);
>>>>>>> f74fb6358978fd1ef1132795bb0f7f54b6df8ee4
        } catch(Exception validationEx) {
            throw validationEx;
        }
    }

<<<<<<< HEAD
    public DashboardUser verifyUser(Long userId, String verificationCode) throws Exception {
        try {
            Optional<DashboardUser> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                throw new UserNotFoundException();
            }
    
            if (verificationCode.equals(user.get().getVerificationCode())) {
                user.get().setVerified(true);
            }
    
            return userRepository.save(user.get());
        } catch (Exception ex) {
            throw ex;
        }
    }

=======
    @Override
>>>>>>> f74fb6358978fd1ef1132795bb0f7f54b6df8ee4
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

<<<<<<< HEAD
    public void setUserRole(String email, String roleName) {
        DashboardUser user = userRepository.findByEmail(email).get();   
=======
    @Override
    public void setUserRole(String email, String roleName) {
        User user = userRepository.findByEmail(email);    
>>>>>>> f74fb6358978fd1ef1132795bb0f7f54b6df8ee4
        Role role = roleRepository.findByName(roleName);
        
        user.getRoles().add(role);
    }
}
