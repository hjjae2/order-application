package com.toy.apps.login.service;

import com.toy.apps.member.entity.Member;
import com.toy.apps.member.exception.MemberFailedLoginException;
import com.toy.apps.member.exception.MemberNotFoundException;
import com.toy.apps.member.repository.MemberRepository;
import com.toy.global.login.CookieLoginHelper;
import com.toy.global.login.LoginManager;
import com.toy.global.login.PasswordBasedCookieLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@RequiredArgsConstructor
@Service
public class SessionMemberLoginService implements PasswordBasedCookieLoginService {

    private final MemberRepository memberRepository;
    private final LoginManager<String, Long> loginManager;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Cookie login(final String email, final String password) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        if (!member.isCorrectPassword(password)) {
            throw new MemberFailedLoginException();
        }

        member.initializeSessionId();
        eventPublisher.publishEvent(member);

        return CookieLoginHelper.generateLoginCookie(member.getSessionId());
    }

    @Override
    public Cookie logout(final String key) {
        loginManager.delete(key);

        return CookieLoginHelper.generateLogoutCookie();
    }
}
