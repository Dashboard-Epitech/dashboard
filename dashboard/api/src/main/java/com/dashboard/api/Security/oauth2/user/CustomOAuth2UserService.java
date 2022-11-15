package com.dashboard.api.Security.oauth2.user;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.DashboardUser;
import com.dashboard.api.Exception.OAuth2AuthenticationProcessingException;
import com.dashboard.api.Model.Enum.AuthProviderEnum;
import com.dashboard.api.Repository.DashboardUserRepository;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private DashboardUserRepository dashboardUserRepository;

    private final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) throws OAuth2AuthenticationProcessingException {
        CustomOAuth2UserInfo oAuth2UserInfo = CustomOAuth2UserInfoFactory.getCustomOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());

        logger.info("REGISTRATION ID : " + oAuth2UserRequest.getClientRegistration().getClientId());
        logger.info("USERINFO CLASS : " + oAuth2UserInfo.getClass());
        logger.info("USER DATA : " + oAuth2User.getAttributes().toString());

        if(oAuth2UserInfo.getEmail().isEmpty()) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<DashboardUser> userOptional = dashboardUserRepository.findByEmail(oAuth2UserInfo.getEmail());
        DashboardUser user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(AuthProviderEnum.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return DashboardUserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private DashboardUser registerNewUser(OAuth2UserRequest oAuth2UserRequest, CustomOAuth2UserInfo oAuth2UserInfo) {
        DashboardUser user = new DashboardUser();

        user.setProvider(AuthProviderEnum.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setUsername(oAuth2UserInfo.getUsername());
        user.setEmail(oAuth2UserInfo.getEmail());

        return dashboardUserRepository.save(user);
    }

    private DashboardUser updateExistingUser(DashboardUser existingUser, CustomOAuth2UserInfo oAuth2UserInfo) {
        existingUser.setUsername(oAuth2UserInfo.getUsername());
        return dashboardUserRepository.save(existingUser);
    }

}
