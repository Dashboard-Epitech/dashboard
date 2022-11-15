package com.dashboard.api.Security.oauth2.google;

import java.util.Map;

import com.dashboard.api.Security.oauth2.user.CustomOAuth2UserInfo;

public class GoogleOAuth2UserInfo extends CustomOAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getUsername() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
}