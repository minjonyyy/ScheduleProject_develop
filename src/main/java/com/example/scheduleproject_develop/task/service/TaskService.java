package com.example.scheduleproject_develop.task.service;

import com.example.scheduleproject_develop.task.dto.TaskResponseDto;
import com.example.scheduleproject_develop.task.entity.Task;
import com.example.scheduleproject_develop.task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public TaskResponseDto findTaskById(Long id) {
        Task findTask = taskRepository.findByIdOrElseThrow(id);

        return new TaskResponseDto(findTask.getId(), findTask.getUsername(), findTask.getTitle(),findTask.getContents());
    }

    @Transactional
    public void updateTask(Long id, String title, String contents) {
        Task findTask = taskRepository.findByIdOrElseThrow(id);
        findTask.updateTask(title, contents);
    }
}
