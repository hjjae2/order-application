package com.toy.global.login;

/**
 * 로그인(로그아웃) 서비스 인터페이스
 * */
public interface LoginService<R> {

    R login(String id);

    R login(String id, String password);
}
