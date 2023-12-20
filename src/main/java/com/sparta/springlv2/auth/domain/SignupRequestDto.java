package com.sparta.springlv2.auth.domain;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @Size(min=4, max = 10, message = "4자 이상 10자 이하여야 합니다.")
    @Pattern(regexp = "^[a-z0-9]*$")
    private String username;

    @Size(min=8, max = 15, message = "8자 이상 15자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;
}
