package com.toy.global.security;

import com.toy.global.login.CookieLoginHelper;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToyAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService toyMemberDetailService;

    public ToyAuthenticationFilter(UserDetailsService toyMemberDetailService) {
        this.toyMemberDetailService = toyMemberDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        AbstractAuthenticationToken toyAuthenticationToken = null;

        try {
            Cookie loginCookie = CookieLoginHelper.fromBy(request);

            UserDetails toyMemberDetails = toyMemberDetailService.loadUserByUsername(loginCookie.getValue());

            toyAuthenticationToken = new ToyAuthenticationToken(toyMemberDetails, toyMemberDetails.getAuthorities());
        } catch (Exception e) {
            // Do Nothing, or set authenticated empty token.
            // e.g. `toyAuthenticationToken = toyAuthenticationToken.authenticatedEmptyToken();`
        } finally {
            SecurityContextHolder.getContext().setAuthentication(toyAuthenticationToken);

            filterChain.doFilter(request, response);
        }
    }
}
