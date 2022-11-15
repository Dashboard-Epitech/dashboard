package com.dashboard.api.Security.oauth2.github;

import java.util.Map;

import com.dashboard.api.Security.oauth2.user.CustomOAuth2UserInfo;

public class GithubOAuth2UserInfo extends CustomOAuth2UserInfo {

    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return ((Integer) attributes.get("id")).toString();
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
