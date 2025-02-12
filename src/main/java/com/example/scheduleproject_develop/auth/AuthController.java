package com.example.scheduleproject_develop.auth;

import com.example.scheduleproject_develop.auth.dto.SignupRequestDto;
import com.example.scheduleproject_develop.common.Const;
import com.example.scheduleproject_develop.auth.dto.LoginRequestDto;
import com.example.scheduleproject_develop.auth.dto.LoginResponseDto;
import com.example.scheduleproject_develop.user.dto.UserResponseDto;
import com.example.scheduleproject_develop.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService loginService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false); //기존 세션이 없으면 null 반환

        if(session != null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "회원가입은 로그아웃 상태에서만 가능합니다.");
        }

        UserResponseDto userResponseDto = loginService.signup(requestDto.getEmail(), requestDto.getUsername(), requestDto.getPassword());
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false); //기존 세션이 없으면 null 반환

        if(session != null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이미 로그인되어 있습니다. 로그아웃 후 다시 시도해주세요.");
        }

        LoginResponseDto user = loginService.login(requestDto);

        Long userId = user.getUserId();

        HttpSession newSession = request.getSession(); // 기존 세션 없으면 새로운 세션 생성

        UserResponseDto loginUser = userService.findUserById(userId);

        newSession.setAttribute(Const.LOGIN_USER, loginUser);
        newSession.setAttribute(Const.LOGIN_USER_ID, userId);

        return ResponseEntity.ok(loginUser.getUsernamme()+"님 로그인 성공!");

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

}
