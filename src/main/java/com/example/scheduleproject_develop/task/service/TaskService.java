package com.example.scheduleproject_develop.task.service;

import com.example.scheduleproject_develop.task.dto.TaskResponseDto;
import com.example.scheduleproject_develop.task.entity.Task;
import com.example.scheduleproject_develop.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskResponseDto createTask(String username, String title, String contents) {

        Task task = new Task(username, title, contents);
        Task savedTask = taskRepository.save(task);

        return new TaskResponseDto(savedTask.getId(), savedTask.getUsername(), savedTask.getTitle(),savedTask.getContents());
    }

    public List<TaskResponseDto> findAllTasks() {
        return taskRepository.findAll().stream().map(TaskResponseDto::toDto).toList();
    }
}
