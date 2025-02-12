package com.example.scheduleproject_develop.task.dto;

import com.example.scheduleproject_develop.task.Task;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskResponseDto {
    private final Long taskId;
    private final String username;
    private final String title;
    private final String contents;
    private final Long numOfComments;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TaskResponseDto(Long taskId, String username, String title, String contents, Long numOfComments, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.taskId = taskId;
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.numOfComments = numOfComments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public static TaskResponseDto toDto(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getUsername(),
                task.getTitle(),
                task.getContents(),
                task.getNumOfComments(),
                task.getCreatedAt(),
                task.getModifiedAt());
    }

}
