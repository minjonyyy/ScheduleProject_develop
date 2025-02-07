package com.example.scheduleproject_develop.user.controller;

import com.example.scheduleproject_develop.common.Const;
import com.example.scheduleproject_develop.user.dto.LoginRequestDto;
import com.example.scheduleproject_develop.user.dto.LoginResponseDto;
import com.example.scheduleproject_develop.user.dto.UserResponseDto;
import com.example.scheduleproject_develop.user.service.LoginService;
import com.example.scheduleproject_develop.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        LoginResponseDto user = loginService.login(requestDto.getEmail(), requestDto.getPassword());
        Long userId = user.getUserId();

        HttpSession session = request.getSession();

        UserResponseDto loginUser = userService.findUserById(userId);

        session.setAttribute(Const.LOGIN_USER, loginUser);
        session.setAttribute(Const.LOGIN_USER_ID, userId);

        return ResponseEntity.ok(loginUser.getUsernamme()+"님 로그인 성공!");

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}
