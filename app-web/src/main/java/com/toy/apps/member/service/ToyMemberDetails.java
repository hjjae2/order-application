package com.toy.apps.member.service;

import com.toy.apps.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class ToyMemberDetails implements UserDetails {

    private final Long id;
    private final String email;
    private final Member.Type type;
    private final List<SimpleGrantedAuthority> authorities;

    private ToyMemberDetails(Long id, String email, Member.Type type) {
        this.id = id;
        this.email = email;
        this.type = type;

        this.authorities = new ArrayList<>();
        this.authorities.add(new SimpleGrantedAuthority(type.name()));
    }

    public static ToyMemberDetails of(Member member) {
        return new ToyMemberDetails(member.getId(), member.getEmail(), member.getType());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}