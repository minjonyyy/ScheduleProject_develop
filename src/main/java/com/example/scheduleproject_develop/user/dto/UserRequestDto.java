package com.example.scheduleproject_develop.user.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {

    private String username;
    private String password;

    public UserRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
