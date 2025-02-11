package com.example.scheduleproject_develop.comment.dto;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String content;

    public CommentResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
