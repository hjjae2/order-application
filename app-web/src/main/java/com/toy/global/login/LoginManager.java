package com.toy.global.login;

import com.toy.apps.member.service.ToyMemberDetailService;

/**
 * 로그인(Login) 상태 관리 인터페이스 <br><br>
 *
 * 1. 로그인 시, LoginManager 에 저장합니다. <br>
 * 2. SecurityFilter 로그인 검증 시, LoginManager 내용 조회합니다. <br><br>
 *
 * 참고 : {@link ToyMemberDetailService}
 */
public interface LoginManager<K, T> {

    T get(K key);

    void save(K key, T login);

    void delete(K key);
}
