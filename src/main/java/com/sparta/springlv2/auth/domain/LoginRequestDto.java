package com.sparta.springlv2.auth.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto{
    private String username;
    private String password;
}
