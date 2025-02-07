package com.example.scheduleproject_develop.auth.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String email;
    private String username;
    private String password;

    public SignupRequestDto(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
