package com.dashboard.api.Service;

import java.util.Arrays;
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
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }

    public DashboardUser getUserByEmail(String email) throws UserNotFoundException {
        DashboardUser user = userRepository.findByEmail(email).get();
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public List<DashboardUser> getUsers() throws NoDataFoundException {
        List<DashboardUser> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }

        return users;
    }

    public void removeUser(Long id) throws UserNotFoundException {
        Optional<DashboardUser> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        userRepository.delete(user.get());
    }

    public DashboardUser editUser(Long id, DashboardUser userFormData) throws UserNotFoundException {
        Optional<DashboardUser> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        user.get()
            .setUsername(userFormData.getUsername())
            .setEmail(userFormData.getEmail())
            .setPassword(userFormData.getPassword());
        
        return userRepository.save(user.get());
    }
    
    public DashboardUser registerUser(DashboardUser user) throws Exception {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList(roleRepository.findByName("USER")));

            String verificationCode = RandomString.make(64);
            user.setVerificationCode(verificationCode);

            userRepository.save(user);

            mailService.sendVerificationMail(user, verificationCode);

            return user;
        } catch(Exception validationEx) {
            throw validationEx;
        }
    }

    public DashboardUser verifyUser(Long userId, String verificationCode) throws Exception {
        try {
            Optional<DashboardUser> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                throw new UserNotFoundException();
            }
    
            if (verificationCode.equals(user.get().getVerificationCode())) {
                user.get().setVerified(true);
                user.get().setVerificationCode(null);
            }
    
            return userRepository.save(user.get());
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    public void setUserRole(String email, String roleName) {
        DashboardUser user = userRepository.findByEmail(email).get();   
        Role role = roleRepository.findByName(roleName);
        
        user.getRoles().add(role);
    }
}