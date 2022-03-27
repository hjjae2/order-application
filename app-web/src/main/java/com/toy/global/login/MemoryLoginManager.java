package com.toy.global.login;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * InMemory 기반 로그인(Login) 상태 관리 클래스
 */
@Component
public class MemoryLoginManager<K, T> implements LoginManager<K, T> {

    private final Map<K, T> loginContext = new HashMap<>();

    @Override
    public void save(K key, T member) {
        loginContext.put(key, member);
    }

    @Override
    public void delete(K key) {
        loginContext.remove(key);
    }

    @Override
    public T get(K key) {
        return loginContext.get(key);
    }
}
