package com.example.scheduleproject_develop.auth;

import com.example.scheduleproject_develop.auth.dto.LoginRequestDto;
import com.example.scheduleproject_develop.auth.dto.LoginResponseDto;
import com.example.scheduleproject_develop.config.PasswordEncoder;
import com.example.scheduleproject_develop.user.dto.UserResponseDto;
import com.example.scheduleproject_develop.user.User;
import com.example.scheduleproject_develop.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto signup(String email, String username, String password) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이미 존재하는 이메일입니다.");
        });

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(email, username, encodedPassword);
        User createdUser = userRepository.save(user);

        return new UserResponseDto(createdUser.getUserId(), createdUser.getUsername());
    }


    public LoginResponseDto login(LoginRequestDto requestDto) {
        User findUser = userRepository.findUserByEmailOrElseThrow(requestDto.getEmail());

        if(!passwordEncoder.matches(requestDto.getPassword(), findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return new LoginResponseDto(findUser.getUserId());

    }
}
