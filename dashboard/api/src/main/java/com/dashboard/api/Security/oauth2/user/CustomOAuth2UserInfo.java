package com.dashboard.api.Security.oauth2.user;

import java.util.Map;

public abstract class CustomOAuth2UserInfo {
    protected Map<String, Object> attributes;

    public CustomOAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getUsername();

    public abstract String getEmail();
}
