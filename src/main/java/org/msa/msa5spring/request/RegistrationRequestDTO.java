package org.msa.msa5spring.request;

import jakarta.validation.constraints.*;

public record RegistrationRequestDTO(
        // 유효성 검사 방법
        @NotBlank(message = "이메일 필수") // 필수 작성 / 빈문자열도 막음.
        // @NotEmpty // null인지 아닌지만 확인함.
        @Pattern(regexp = "^[a-z]{4,8}$", message = "이메일 패턴 틀림")
        String email,
        @NotBlank(message = "비밀번호 필수")
        @Size(min = 2,max = 10,message = "비밀번호는 2글자에서 10글자로 입력") // String일때 Size로 최소 입력 최대 입력 조절
        String password,

        String name,
        @Min(value = 0, message = "나이는 최소 0살부터 입니다.")
        @Max(value = 999, message = "나이는 최대 999살까지입니다.")
        Integer age
) {}

