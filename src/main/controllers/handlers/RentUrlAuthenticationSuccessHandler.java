package main.controllers.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class RentUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    private static final Logger logger = LogManager.getLogger(RentUrlAuthenticationSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException
    {
        handle(request, response, authentication);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response,
                        Authentication authentication) throws IOException
    {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to "
                    + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
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
            return "/homepage.html";
        } else if (isAdmin) {
            return "/console.html";
        } else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null)
            return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
