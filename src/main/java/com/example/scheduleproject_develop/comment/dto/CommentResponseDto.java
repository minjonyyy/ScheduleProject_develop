package com.example.scheduleproject_develop.comment.dto;

import com.example.scheduleproject_develop.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String content;
    private final Long taskId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CommentResponseDto(Long id, String content, Long taskId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.taskId = taskId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getTask().getId(),
                comment.getCreatedAt(),
                comment.getModifiedAt());
    }
}
