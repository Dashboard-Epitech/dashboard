package com.dashboard.api.Security.oauth2.user;

import java.util.Map;

import com.dashboard.api.Exception.OAuth2AuthenticationProcessingException;
import com.dashboard.api.Model.Enum.AuthProviderEnum;
import com.dashboard.api.Security.oauth2.github.GithubOAuth2UserInfo;
import com.dashboard.api.Security.oauth2.google.GoogleOAuth2UserInfo;

public class CustomOAuth2UserInfoFactory {
    
    public static CustomOAuth2UserInfo getCustomOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws OAuth2AuthenticationProcessingException{
        if (registrationId.equalsIgnoreCase(AuthProviderEnum.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProviderEnum.github.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Login with " + registrationId + " is not supported.");
        }
    }
}
