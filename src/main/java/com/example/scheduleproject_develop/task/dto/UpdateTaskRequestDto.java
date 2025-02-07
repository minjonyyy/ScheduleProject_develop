package com.example.scheduleproject_develop.task.dto;

import lombok.Getter;

@Getter
public class UpdateTaskRequestDto {

    private final String title;
    private final String contents;

    public UpdateTaskRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
