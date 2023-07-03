package com.lv2.spartalv2hw.dto;

import lombok.Getter;

@Getter
public class LoginResponseEntity {
    private String msg = "로그인 성공";
    private int statusCode = 200;
}
