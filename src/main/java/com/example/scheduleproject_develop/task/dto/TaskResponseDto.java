package com.example.scheduleproject_develop.task.dto;

import com.example.scheduleproject_develop.task.entity.Task;
import lombok.Getter;

@Getter
public class TaskResponseDto {
    private final Long id;
    private final String username;
    private final String title;
    private final String contents;

    public TaskResponseDto(Long id, String username, String title, String contents) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    public static TaskResponseDto toDto(Task task) {
        return new TaskResponseDto(task.getId(), task.getUsername(), task.getTitle(), task.getContents());
    }

}
