package com.toy.apps.member.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.toy.apps.member.exception.MemberFailedLoginException;
import com.toy.apps.member.exception.MemberValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Type type;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

    @Transient
    private transient String sessionId;

    /**
     * 고객 유형 <br>
     * - NORMAL : 일반 고객 <br>
     * - SELLER : 판매 고객 <br>
     * - ADMIN  : 관리자
     */
    public enum Type {
        NORMAL, SELLER, ADMIN;

        private static final Map<String, Type> nameMap = new HashMap<>();

        static {
            for (Type type : Type.values()) {
                nameMap.put(type.name(), type);
            }
        }

        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        public static Type fromBy(String name) {
            if (!nameMap.containsKey(name)) {
                return null;
            }

            return nameMap.get(name);
        }
    }

    public void initializeForJoin() {
        encryptPassword();
    }

    public void initializeSessionId() {
        this.sessionId = UUID.randomUUID().toString();
    }

    private void encryptPassword() {
        validatePassword();

        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }

    public boolean isCorrectPassword(String password) {
        return BCrypt.checkpw(password, this.getPassword());
    }

    /**
     * 정책에 따른 유효성 검사 로직을 추가/수정합니다. <br>
     * (* 현재 : 기본적으로 필요하다고 판단되는 것들을 체크하도록 하였습니다.)
     */
    public void validateForJoin() {
        // 회원 가입(save)을 위한 Validation(검증) 진행합니다.
        validateEmail();
        validateType();
        validatePassword();
    }

    private void validateEmail() {
        if (this.email == null || this.email.isBlank()) {
            throw new MemberValidationException("이메일은 빈 값이 될 수 없습니다.");
        }
        // 새로운 유효성 검사를 여기에 추가합니다. //
    }

    private void validateType() {
        if (this.type == null) {
            throw new MemberValidationException("사용자 구분 값은 빈 값이 될 수 없습니다.");
        }
        // 새로운 유효성 검사를 여기에 추가합니다. //
    }

    private void validatePassword() {
        if (this.password == null) {
            throw new MemberValidationException("비밀번호 값은 빈 값이 될 수 없습니다.");
        }
        // 새로운 유효성 검사를 여기에 추가합니다. //
    }

    public void login(ApplicationEventPublisher applicationEventPublisher) {
        initializeSessionId();

        if (sessionId == null) {
            throw new MemberFailedLoginException("로그인 세션이 정상적으로 생성되지 않았습니다.");
        }

        applicationEventPublisher.publishEvent(this);
    }
}
