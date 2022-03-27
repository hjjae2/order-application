package com.toy.apps.member.web;

import com.toy.apps.member.dto.MemberDto;
import com.toy.apps.member.service.MemberJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberApiController {

    private final MemberJoinService memberJoinService;

    /**
     * 회원가입 API
     */
    @PostMapping
    public MemberDto.Create.ResponseDto join(@Valid @RequestBody MemberDto.Create.RequestDto requestDto) {
        return memberJoinService.join(requestDto);
    }
}
