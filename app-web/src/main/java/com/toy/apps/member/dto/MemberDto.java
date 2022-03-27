package com.toy.apps.member.dto;

import com.toy.apps.member.entity.Member;
import com.toy.modules.validator.Enum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MemberDto {

    public static class Create {
        @AllArgsConstructor
        @Builder
        @Getter
        public static class RequestDto {
            @Email(message = "이메일을 정확히 입력해주세요.")
            String email;
            @NotBlank(message = "비밀번호는 빈 값이 될 수 없습니다.")
            String password;
            @Enum(type = Member.Type.class, message = "사용자 구분 값을 정확히 입력해주세요.")
            Member.Type type;

            public Member toEntity() {
                return Member.builder()
                             .email(email)
                             .password(password)
                             .type(type)
                             .build();
            }
        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ResponseDto {

            Long id;
            String email;
            Member.Type type;

            public static ResponseDto of(Member member) {
                return ResponseDto.builder()
                                  .id(member.getId())
                                  .email(member.getEmail())
                                  .type(member.getType())
                                  .build();
            }
        }
    }

    public static class Read {
        @AllArgsConstructor
        @Builder
        @Getter
        public static class RequestDto {
            String email;
        }

        @AllArgsConstructor
        @Builder
        @Getter
        public static class ResponseDto {

            Long id;
            String email;
            Member.Type type;

            public static ResponseDto of(Member member) {
                return ResponseDto.builder()
                                  .id(member.getId())
                                  .email(member.getEmail())
                                  .type(member.getType())
                                  .build();
            }
        }
    }

    /**
     * 기능 추가 시 작성합니다.
     */
    public static class Update {
    }

    /**
     * 기능 추가 시 작성합니다.
     */
    public static class Delete {
    }
}
