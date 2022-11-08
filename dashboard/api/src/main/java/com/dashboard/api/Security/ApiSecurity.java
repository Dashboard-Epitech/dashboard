package com.dashboard.api.Security;

import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.dashboard.api.Repository.DashboardUserRepository;

 
@Configuration
public class ApiSecurity {
     
    @Autowired private DashboardUserRepository dashboardUserRepository;
    // @Autowired private JwtTokenFilter jwtTokenFilter;
     
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
             
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return dashboardUserRepository.findByEmail(username)
                        .orElse(dashboardUserRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException(username))
                        );
            }
        };
    }
     
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
     
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         
        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated();
        // http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
         
        return http.build();
    }
}
