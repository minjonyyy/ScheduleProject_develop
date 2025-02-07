package com.example.scheduleproject_develop.task.controller;

import com.example.scheduleproject_develop.task.dto.CreateTaskRequestDto;
import com.example.scheduleproject_develop.task.dto.TaskResponseDto;
import com.example.scheduleproject_develop.task.dto.UpdateTaskRequestDto;
import com.example.scheduleproject_develop.task.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<TaskResponseDto> createTask(HttpServletRequest request, @RequestBody CreateTaskRequestDto requestDto) {
        TaskResponseDto taskResponseDto =
                taskService.createTask(
                        request,
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

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> findTaskById(@PathVariable Long taskId){
        TaskResponseDto taskByIdDto = taskService.findTaskById(taskId);
        return new ResponseEntity<>(taskByIdDto, HttpStatus.OK);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(
            @PathVariable Long taskId,
            @RequestBody UpdateTaskRequestDto requestDto
            ){
        taskService.updateTask(taskId, requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
