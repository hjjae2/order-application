package com.toy.apps.login.service;

import com.toy.apps.member.entity.Member;
import com.toy.apps.member.service.ToyMemberDetails;
import com.toy.global.login.LoginEventHandler;
import com.toy.global.login.LoginManager;
import com.toy.global.security.ToyAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SessionMemberLoginEventHandler implements LoginEventHandler {

    private final LoginManager<String, Long> loginManager;

    @EventListener
    public void login(Member member) {
        loginManager.save(member.getSessionId(), member.getId());

        ToyMemberDetails toyMemberDetails = ToyMemberDetails.of(member);
        SecurityContextHolder.getContext().setAuthentication(
                new ToyAuthenticationToken(toyMemberDetails, toyMemberDetails.getAuthorities())
        );
    }
}
