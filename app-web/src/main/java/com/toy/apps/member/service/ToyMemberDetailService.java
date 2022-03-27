package com.toy.apps.member.service;

import com.toy.apps.member.entity.Member;
import com.toy.apps.member.repository.MemberRepository;
import com.toy.global.login.LoginManager;
import com.toy.global.security.ToyAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ToyMemberDetailService implements UserDetailsService {

    private final LoginManager<String, Long> loginManager;
    private final MemberRepository memberRepository;

    @Override
    public ToyMemberDetails loadUserByUsername(String key) throws UsernameNotFoundException {
        Long memberId = loginManager.get(key);
        Member member = memberRepository.findById(memberId).orElseThrow(ToyAuthenticationException::new);

        return ToyMemberDetails.of(member);
    }
}