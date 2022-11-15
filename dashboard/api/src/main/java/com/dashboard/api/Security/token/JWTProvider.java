package com.dashboard.api.Security.token;

import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.dashboard.api.Configuration.AppProperties;
import com.dashboard.api.Security.oauth2.user.DashboardUserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTProvider {
    public static final Logger logger = LoggerFactory.getLogger(JWTProvider.class);

    private AppProperties appProperties;

    public JWTProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        DashboardUserPrincipal userPrincipal = (DashboardUserPrincipal) authentication.getPrincipal();
        
        Date now = new Date();
        Date expires = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                   .setSubject(Long.toString(userPrincipal.getId()))
                   .setIssuedAt(new Date())
                   .setExpiration(expires)
                   .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                   .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(appProperties.getAuth().getTokenSecret())
                            .parseClaimsJws(token)
                            .getBody();
        
        return Long.parseLong(claims.getSubject());
    }
    
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getClass() + " : " + ex.getMessage());
        }

        return false;
    }
}
