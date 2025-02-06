package com.example.scheduleproject_develop.task.dto;

import lombok.Getter;

@Getter
public class CreateTaskRequestDto {

    private final String username;
    private final String title;
    private final String contents;

    public CreateTaskRequestDto(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
