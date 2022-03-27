package com.toy.apps.member.service;

import com.toy.apps.member.entity.Member;
import com.toy.apps.member.dto.MemberDto;
import com.toy.apps.member.exception.MemberEmailException;
import com.toy.apps.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberJoinService {

    private final MemberRepository memberRepository;

    /**
     * [회원 가입] <br><br>
     * 1. 회원 가입 초기 값 설정 (Salt, Digest) <br>
     * 2. 데이터 검증 <br>
     * 3. 회원 가입 완료 (DB 저장) <br>
     */
    @Transactional
    public MemberDto.Create.ResponseDto join(final MemberDto.Create.RequestDto requestDto) {
        Member member = requestDto.toEntity();

        memberRepository.findByEmail(member.getEmail()).ifPresent((m) -> {
            throw new MemberEmailException("이미 사용중인 이메일입니다.");
        });

        member.initializeForJoin();
        member.validateForJoin();

        member = memberRepository.save(member);

        System.out.println(member.getEmail());
        System.out.println(member.getPassword());

        return MemberDto.Create.ResponseDto.of(member);
    }
}
