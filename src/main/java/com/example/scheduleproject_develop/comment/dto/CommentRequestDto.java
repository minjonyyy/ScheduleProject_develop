package com.example.scheduleproject_develop.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private String content;


    public CommentRequestDto(String content) {
        this.content = content;
    }
}
