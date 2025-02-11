package com.example.scheduleproject_develop.task;

import com.example.scheduleproject_develop.common.Const;
import com.example.scheduleproject_develop.task.dto.TaskResponseDto;
import com.example.scheduleproject_develop.user.entity.User;
import com.example.scheduleproject_develop.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskResponseDto createTask(HttpServletRequest request, String username, String title, String contents) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(Const.LOGIN_USER_ID);

        User user = userRepository.findByIdOrElseThrow(userId);

        Task task = new Task(username, title, contents, user);
        Task savedTask = taskRepository.save(task);

        return new TaskResponseDto(savedTask.getTaskId(), savedTask.getUsername(), savedTask.getTitle(),savedTask.getContents());
    }

    public List<TaskResponseDto> findAllTasks() {
        return taskRepository.findAll().stream().map(TaskResponseDto::toDto).toList();
    }

    public TaskResponseDto findTaskById(Long taskId) {
        Task findTask = taskRepository.findByIdOrElseThrow(taskId);

        return new TaskResponseDto(findTask.getTaskId(), findTask.getUsername(), findTask.getTitle(),findTask.getContents());
    }

    @Transactional
    public void updateTask(Long taskId, String title, String contents) {
        Task findTask = taskRepository.findByIdOrElseThrow(taskId);
        findTask.updateTask(title, contents);
    }

    public void deleteTask(Long taskId) {
        Task findTask = taskRepository.findByIdOrElseThrow(taskId);
        taskRepository.delete(findTask);
    }
}
