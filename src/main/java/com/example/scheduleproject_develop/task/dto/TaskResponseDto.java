package com.example.scheduleproject_develop.task.dto;

import com.example.scheduleproject_develop.task.Task;
import lombok.Getter;

@Getter
public class TaskResponseDto {
    private final Long taskId;
    private final String username;
    private final String title;
    private final String contents;

    public TaskResponseDto(Long taskId, String username, String title, String contents) {
        this.taskId = taskId;
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    public static TaskResponseDto toDto(Task task) {
        return new TaskResponseDto(task.getTaskId(), task.getUsername(), task.getTitle(), task.getContents());
    }

}
