package com.toy.apps.login.web;

import com.toy.apps.login.dto.MemberLoginDto;
import com.toy.global.login.CookieLoginHelper;
import com.toy.global.login.CookieLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberLoginApiController {

    private final CookieLoginService loginService;

    @PostMapping("/login")
    public MemberLoginDto.Login.ResponseDto login(HttpServletResponse response,
                                                  @Valid @RequestBody MemberLoginDto.Login.RequestDto requestDto) {
        Cookie loginCookie = loginService.login(requestDto.getEmail(), requestDto.getPassword());
        response.addCookie(loginCookie);

        return MemberLoginDto.Login.ResponseDto.success();
    }

    @GetMapping("/logout")
    public MemberLoginDto.Logout.ResponseDto logout(HttpServletRequest request,
                                                    HttpServletResponse response) {
        Cookie loginCookie = CookieLoginHelper.fromBy(request);
        Cookie logoutCookie = loginService.logout(loginCookie.getValue());
        response.addCookie(logoutCookie);

        return MemberLoginDto.Logout.ResponseDto.success();
    }
}
