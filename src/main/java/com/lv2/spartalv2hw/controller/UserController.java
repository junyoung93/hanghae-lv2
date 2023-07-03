package com.lv2.spartalv2hw.controller;


import com.lv2.spartalv2hw.dto.LoginResponseEntity;
import com.lv2.spartalv2hw.dto.LoginRequestDto;
import com.lv2.spartalv2hw.dto.SignupRequestDto;
import com.lv2.spartalv2hw.dto.SignupResponseEntity;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return new ResponseEntity("회원가입에 실패하셨습니다.", HttpStatusCode.valueOf(400));
//            return new SignupRequestDto("게시글 작성자만 삭제할수 있습니다"));
        }
        userService.signup(signupRequestDto);

       return new ResponseEntity(new SignupResponseEntity(), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        try {
            userService.login(loginRequestDto, res);
        } catch (Exception e) {
            return new ResponseEntity("로그인에 실패하셨습니다.", HttpStatusCode.valueOf(400));

        }
        return new ResponseEntity(new LoginResponseEntity(), HttpStatusCode.valueOf(200));
    }
}
