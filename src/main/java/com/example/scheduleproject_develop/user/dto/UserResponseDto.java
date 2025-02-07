package com.example.scheduleproject_develop.user.dto;

import com.example.scheduleproject_develop.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long id;
    private final String usernamme;

    public UserResponseDto(Long id, String usernamme) {
        this.id = id;
        this.usernamme = usernamme;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername());
    }
}
