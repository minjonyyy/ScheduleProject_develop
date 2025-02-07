package com.example.scheduleproject_develop.user.service;

import com.example.scheduleproject_develop.user.dto.LoginResponseDto;
import com.example.scheduleproject_develop.user.entity.User;
import com.example.scheduleproject_develop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public LoginResponseDto login(String email, String passsword){
        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        if(!findUser.getPassword().equals(passsword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponseDto(findUser.getUserId());

    }
}
