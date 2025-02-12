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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService loginService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto){
        UserResponseDto userResponseDto = loginService.signup(requestDto.getEmail(), requestDto.getUsername(), requestDto.getPassword());
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        LoginResponseDto user = loginService.login(requestDto.getEmail(), requestDto.getPassword());
        Long userId = user.getUserId();

        HttpSession session = request.getSession();

        UserResponseDto loginUser = userService.findUserById(userId);

        session.setAttribute(Const.LOGIN_USER, loginUser);
        session.setAttribute(Const.LOGIN_USER_ID, userId);

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
