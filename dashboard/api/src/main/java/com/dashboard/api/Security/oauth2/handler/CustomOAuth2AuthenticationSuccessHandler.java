package com.dashboard.api.Security.oauth2.handler;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.dashboard.api.Configuration.AppProperties;
import com.dashboard.api.Security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.dashboard.api.Security.token.JWTProvider;
import com.dashboard.api.Utils.CookieUtils;

@Component
public class CustomOAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private JWTProvider jwtProvider;
    private AppProperties appProperties;
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Autowired
    public CustomOAuth2AuthenticationSuccessHandler(JWTProvider jwtProvider, AppProperties appProperties, 
        HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
            this.jwtProvider = jwtProvider;
            this.appProperties = appProperties;
            this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response already committed. Unable to redirect");
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            logger.error("Unauthorized Redirect URI");
            return null;
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
        String token = jwtProvider.createToken(authentication);

        return UriComponentsBuilder.fromUriString(targetUrl)
                    .queryParam("token", token)
                    .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);

        return appProperties.getOAuth2().getAuthorizedRedirectUris()
                    .stream()
                    .anyMatch(authorizedRedirectUri -> {
                        URI authorizedUri = URI.create(authorizedRedirectUri);

                        if (authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                                && authorizedUri.getPort() == clientRedirectUri.getPort()) {
                                    return true;
                        }

                        return false;
                    });
    }
}
