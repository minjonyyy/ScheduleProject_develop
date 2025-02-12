package com.example.scheduleproject_develop.task;

import com.example.scheduleproject_develop.task.dto.CreateTaskRequestDto;
import com.example.scheduleproject_develop.task.dto.TaskResponseDto;
import com.example.scheduleproject_develop.task.dto.UpdateTaskRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<TaskResponseDto> createTask(HttpServletRequest request, @Valid @RequestBody CreateTaskRequestDto requestDto) {
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
    public ResponseEntity<TaskResponseDto> findTaskById(@PathVariable("taskId") Long taskId){
        TaskResponseDto taskByIdDto = taskService.findTaskById(taskId);
        return new ResponseEntity<>(taskByIdDto, HttpStatus.OK);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody UpdateTaskRequestDto requestDto
            ){
        taskService.updateTask(taskId, requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<Task>> findTasksByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Page<Task> tasksByPage = taskService.findTasksByPage(page, size);
        return new ResponseEntity<>(tasksByPage, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskResponseDto>> searchTask(@RequestParam String keyword){
        List<TaskResponseDto> taskResponseDtoList = taskService.searchTask(keyword);
        return new ResponseEntity<>(taskResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<List<TaskResponseDto>> searchTaskByUser(@RequestParam String username){
        List<TaskResponseDto> taskResponseDtoList = taskService.searchTaskByUser(username);
        return new ResponseEntity<>(taskResponseDtoList, HttpStatus.OK);
    }

}
