package com.dashboard.api.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.DashboardUser;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GithubService {
    @Value("${GITHUB_API_CLIENT_ID}")
    private String clientId;

    @Value("${GITHUB_API_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${GITHUB_API_CLIENT_REDIRECT}")
    private String clientRedirect;

    public String getLoginPage() {
        return "https://github.com/login/oauth/authorize?client_id=" + clientId;
    }
}
