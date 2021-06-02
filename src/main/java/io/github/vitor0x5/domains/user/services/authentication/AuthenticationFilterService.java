package io.github.vitor0x5.domains.user.services.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthenticationFilterService extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserAuthenticateService userAuthenticateService;

    public AuthenticationFilterService(JwtService jwtService, UserAuthenticateService userAuthenticateService) {
        this.jwtService = jwtService;
        this.userAuthenticateService = userAuthenticateService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        Cookie token = WebUtils.getCookie(httpServletRequest, "token");
        if(token != null) {
            String jwt = token.getValue();
            if(jwtService.tokenIsValid(jwt)) {
                httpServletRequest.setAttribute("userEmail", jwtService.getLoggedUserEmail(jwt));
                setSecurityContext(httpServletRequest, jwt);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void setSecurityContext(HttpServletRequest httpServletRequest, String jwt) {
        String userEmail = jwtService.getLoggedUserEmail(jwt);
        UserDetails user = userAuthenticateService.loadUserByUsername(userEmail);

        UsernamePasswordAuthenticationToken userAuthentication = new
                UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities());

         userAuthentication.setDetails(new
                WebAuthenticationDetailsSource()
                .buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }
}
