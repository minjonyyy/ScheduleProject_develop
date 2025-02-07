package com.example.scheduleproject_develop.task.controller;

import com.example.scheduleproject_develop.task.dto.CreateTaskRequestDto;
import com.example.scheduleproject_develop.task.dto.TaskResponseDto;
import com.example.scheduleproject_develop.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody CreateTaskRequestDto requestDto) {
        TaskResponseDto taskResponseDto =
                taskService.createTask(
                        requestDto.getUsername(),
                        requestDto.getTitle(),
                        requestDto.getContents());

        return new ResponseEntity<>(taskResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> findAllTasks(){
        List<TaskResponseDto> taskResponseDtoList = taskService.findAllTasks();

        return new ResponseEntity<>(taskResponseDtoList, HttpStatus.OK);
    }

}
