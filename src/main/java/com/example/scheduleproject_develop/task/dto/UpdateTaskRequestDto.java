package com.example.scheduleproject_develop.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateTaskRequestDto {

    @NotBlank
    @Size(max=10, message="제목은 최대 10자까지 입력해주세요.")
    private final String title;

    @NotBlank
    private final String contents;

    public UpdateTaskRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
