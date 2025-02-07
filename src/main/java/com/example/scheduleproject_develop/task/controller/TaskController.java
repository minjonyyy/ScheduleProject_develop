package com.example.scheduleproject_develop.task.controller;

import com.example.scheduleproject_develop.task.dto.CreateTaskRequestDto;
import com.example.scheduleproject_develop.task.dto.TaskResponseDto;
import com.example.scheduleproject_develop.task.dto.UpdateTaskRequestDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findTaskById(@PathVariable Long id){
        TaskResponseDto taskByIdDto = taskService.findTaskById(id);
        return new ResponseEntity<>(taskByIdDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateTask(
            @PathVariable Long id,
            @RequestBody UpdateTaskRequestDto requestDto
            ){
        taskService.updateTask(id, requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
