package com.example.scheduleproject_develop.task;

import com.example.scheduleproject_develop.comment.Comment;
import com.example.scheduleproject_develop.comment.CommentRepository;
import com.example.scheduleproject_develop.comment.CommentService;
import com.example.scheduleproject_develop.common.Const;
import com.example.scheduleproject_develop.task.dto.TaskResponseDto;
import com.example.scheduleproject_develop.user.User;
import com.example.scheduleproject_develop.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @Transactional
    public TaskResponseDto createTask(HttpServletRequest request, String username, String title, String contents) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(Const.LOGIN_USER_ID);

        User user = userRepository.findByIdOrElseThrow(userId);

        Task task = new Task(username, title, contents, 0L, user); //할 일 생성 시에는 일단 댓글개수 0개로 설정
        Task savedTask = taskRepository.save(task);

//        return new TaskResponseDto(
//                savedTask.getId(),
//                savedTask.getUsername(),
//                savedTask.getTitle(),
//                savedTask.getContents(),
//                savedTask.getNumOfComments(),
//                savedTask.getCreatedAt(),
//                savedTask.getModifiedAt()
//        );

        return TaskResponseDto.toDto(savedTask);
    }

    public List<TaskResponseDto> findAllTasks() {
        return taskRepository.findAll().stream().map(TaskResponseDto::toDto).toList();
    }

    public TaskResponseDto findTaskById(Long id) {
        Task findTask = taskRepository.findByIdOrElseThrow(id);

//        return new TaskResponseDto(
//                findTask.getId(),
//                findTask.getUsername(),
//                findTask.getTitle(),
//                findTask.getContents(),
//                findTask.getNumOfComments(),
//                findTask.getCreatedAt(),
//                findTask.getModifiedAt()
//        );
        return TaskResponseDto.toDto(findTask);
    }

    @Transactional
    public void updateTask(Long id, String title, String contents) {
        Task findTask = taskRepository.findByIdOrElseThrow(id);
        findTask.updateTask(title, contents);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task findTask = taskRepository.findByIdOrElseThrow(id);
        taskRepository.delete(findTask);
    }

    public Page<Task> findTasksByPage(Pageable pageable) {

        return taskRepository.findAllByOrderByModifiedAtDesc(pageable);

    }

    public List<TaskResponseDto> searchTask(String keyword){
        return taskRepository.findByTitleContaining(keyword).stream().map(TaskResponseDto::toDto).collect(Collectors.toList());

    }

    public List<TaskResponseDto> searchTaskByUser(String username){
        return taskRepository.findByUsername(username).stream().map(TaskResponseDto::toDto).collect(Collectors.toList());
    }


//    public void deleteTasksByUserId(Long userId) {
//        List<Task> taskByUserId = taskRepository.findTaskByUser_UserId(userId);
//        taskRepository.deleteAll(taskByUserId);
//    }

}
