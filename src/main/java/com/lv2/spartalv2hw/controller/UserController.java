package com.lv2.spartalv2hw.controller;


import com.lv2.spartalv2hw.dto.LoginRequestDto;
import com.lv2.spartalv2hw.dto.SignupRequestDto;
import com.lv2.spartalv2hw.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "JSON 변환 오류";
//            return new SignupRequestDto("게시글 작성자만 삭제할수 있습니다"));
        }
        userService.signup(signupRequestDto);

        return "회원가입 완료!";
    }

    @PostMapping("/api/user")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        try {
            userService.login(loginRequestDto, res);
        } catch (Exception e) {
            throw new RuntimeException(e);
//            return "로그인 실패했습니다.";
        }
        return "로그인 성공했습니다.";
    }
}
