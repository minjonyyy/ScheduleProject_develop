package com.example.scheduleproject_develop.auth;

import com.example.scheduleproject_develop.auth.dto.LoginResponseDto;
import com.example.scheduleproject_develop.config.PasswordEncoder;
import com.example.scheduleproject_develop.user.dto.UserResponseDto;
import com.example.scheduleproject_develop.user.entity.User;
import com.example.scheduleproject_develop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signup(String email, String username, String password) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이미 존재하는 이메일입니다.");
        });

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(email, username, encodedPassword);
        User createdUser = userRepository.save(user);

        return new UserResponseDto(createdUser.getUserId(), createdUser.getUsername());
    }

    public LoginResponseDto login(String email, String passsword){
        User findUser = userRepository.findUserByEmailOrElseThrow(email);

//        if(!findUser.getPassword().equals(passsword)){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
//        }

        if(!passwordEncoder.matches(passsword, findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return new LoginResponseDto(findUser.getUserId());

    }
}
