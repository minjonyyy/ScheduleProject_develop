package com.example.scheduleproject_develop.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    private final String email;
    private final String password;

}
