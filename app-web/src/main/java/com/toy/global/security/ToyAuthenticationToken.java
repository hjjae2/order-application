package com.toy.global.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ToyAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    public ToyAuthenticationToken() {
        super(null);
        this.principal = null;
        setAuthenticated(false);
    }

    public ToyAuthenticationToken(UserDetails userDetails) {
        this(userDetails, userDetails.getAuthorities());
    }

    public ToyAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        setAuthenticated(true);

        if (principal == null) {
            setAuthenticated(false);
        }
        if (authorities.size() <= 0) {
            setAuthenticated(false);
        }
    }

    public static ToyAuthenticationToken authenticatedEmptyToken() {
        ToyAuthenticationToken gpAuthenticationToken = new ToyAuthenticationToken();
        gpAuthenticationToken.setAuthenticated(true);

        return gpAuthenticationToken;
    }

    public static ToyAuthenticationToken unauthenticatedEmptyToken() {
        return new ToyAuthenticationToken();
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
