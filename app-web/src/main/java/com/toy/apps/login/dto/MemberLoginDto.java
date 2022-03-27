package com.toy.apps.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MemberLoginDto {

    public static class Login {

        @AllArgsConstructor
        @Builder
        @Getter
        public static class RequestDto {
            @Email(message = "이메일을 정확히 입력해주세요.")
            String email;
            @NotBlank(message = "비밀번호는 빈 값이 될 수 없습니다.")
            String password;
        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ResponseDto {
            private boolean result;

            public static ResponseDto of(boolean result) {
                return ResponseDto.builder().result(result).build();
            }

            public static ResponseDto success() {
                return of(true);
            }

            public static ResponseDto failure() {
                return of(false);
            }
        }
    }

    public static class Logout {
        public static class RequestDto {

        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ResponseDto {
            private boolean result;

            public static ResponseDto of(boolean result) {
                return ResponseDto.builder().result(result).build();
            }

            public static ResponseDto success() {
                return of(true);
            }

            public static ResponseDto failure() {
                return of(false);
            }
        }
    }
}
