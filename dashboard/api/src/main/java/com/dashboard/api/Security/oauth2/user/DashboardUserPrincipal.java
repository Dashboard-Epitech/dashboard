package com.dashboard.api.Security.oauth2.user;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.dashboard.api.Entity.DashboardUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardUserPrincipal implements OAuth2User, UserDetails {
    private Long id; 
    private String email;
    private String username;
    private String password;
    private boolean verified;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public DashboardUserPrincipal(Long id, String email, String username, String password, boolean verified, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.verified = verified;
        this.authorities = authorities;
    }

    public static DashboardUserPrincipal create(DashboardUser user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new DashboardUserPrincipal(
            user.getId(),
            user.getUsername(),
            user.getEmail(), 
            user.getPassword(), 
            user.isVerified(),
            authorities
        );
    }

    public static DashboardUserPrincipal create(DashboardUser user, Map<String, Object> attributes) {
        DashboardUserPrincipal userPrincipal = DashboardUserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);

        return userPrincipal;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return verified;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}
