package main.controllers.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class RoleBasedUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
{
    private final Logger logger = LogManager.getLogger(this.getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException
    {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String targetUrl;
        if (savedRequest == null) {
            targetUrl = determineTargetUrl(authentication);
            logger.debug("Based on role.");
        } else {
            targetUrl = savedRequest.getRedirectUrl();
            logger.debug("Based on previous URL.");
        }

        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParameter != null && StringUtils.hasText(request
                .getParameter(targetUrlParameter)))) {
            logger.debug("Based on default target URL.");
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }

        clearAuthenticationAttributes(request);

        logger.debug("Redirecting to: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String authString = authority.getAuthority();
            if ("ROLE_USER".equals(authString)) {
                isUser = true;
                break;
            } else if ("ROLE_ADMIN".equals(authString)) {
                isAdmin = true;
                break;
            }
        }

        if (isUser) {
            return "/userpage";
        } else if (isAdmin) {
            return "/admin";
        } else {
            throw new IllegalStateException();
        }
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
