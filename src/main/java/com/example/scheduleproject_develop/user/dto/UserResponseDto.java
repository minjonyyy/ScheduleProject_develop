package com.example.scheduleproject_develop.user.dto;

import com.example.scheduleproject_develop.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long userId;
    private final String usernamme;

    public UserResponseDto(Long userId, String usernamme) {
        this.userId = userId;
        this.usernamme = usernamme;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getUserId(), user.getUsername());
    }
}
