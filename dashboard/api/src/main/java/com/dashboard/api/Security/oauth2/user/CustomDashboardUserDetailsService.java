package com.dashboard.api.Security.oauth2.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.DashboardUser;
import com.dashboard.api.Exception.UserNotFoundException;
import com.dashboard.api.Repository.DashboardUserRepository;

@Service
public class CustomDashboardUserDetailsService implements UserDetailsService {
    @Autowired
    DashboardUserRepository dashboardUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        Optional<DashboardUser> optionalUserByEmail = dashboardUserRepository.findByEmail(username);
        Optional<DashboardUser> optionalUserByUsername = dashboardUserRepository.findByUsername(username);

        DashboardUser userByEmail = optionalUserByEmail.isPresent() ? optionalUserByEmail.get() : null;
        DashboardUser userByUsername = optionalUserByUsername.isPresent() ? optionalUserByUsername.get() : null;

        if (userByEmail == null && userByUsername == null) {
            throw new UsernameNotFoundException("No user found with provided email/username.");
        }

        return DashboardUserPrincipal.create(userByEmail == null ? userByUsername : userByEmail);
    }

    @Transactional
    public UserDetails loadUserById(Long id) throws Exception {
        Optional<DashboardUser> user = dashboardUserRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("No user found with provided id");
        }

        return DashboardUserPrincipal.create(user.get());
    }
}
