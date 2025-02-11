package com.example.scheduleproject_develop.comment.dto;

import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {
    private String content;

    public UpdateCommentRequestDto(String content) {
        this.content = content;
    }
}
