package com.todoproject.todoproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Pattern (regexp = "^[a-z0-9]{4,10}$", message = "사용자이름은 영어 소문자와 숫자만 가능하며 4~10 자리여야 합니다.")
    private String username;
    @NotBlank
    @Pattern (regexp = "^[a-zA-Z0-9]{8,15}$", message = "비밀번호는 영어 대소문자와 숫자만 가능하며 8~15 자리여야 합니다.")
    private String password;
}
