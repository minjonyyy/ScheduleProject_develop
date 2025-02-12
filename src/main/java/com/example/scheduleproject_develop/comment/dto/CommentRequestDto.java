package com.example.scheduleproject_develop.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank @Size(max = 50, message = "댓글은 최대 50자까지 입력해주세요.")
    private String content;


    public CommentRequestDto(String content) {
        this.content = content;
    }
}
