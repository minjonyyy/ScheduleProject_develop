package com.example.scheduleproject_develop.comment;

import com.example.scheduleproject_develop.comment.dto.CommentRequestDto;
import com.example.scheduleproject_develop.comment.dto.CommentResponseDto;
import com.example.scheduleproject_develop.common.Const;
import com.example.scheduleproject_develop.task.Task;
import com.example.scheduleproject_develop.task.TaskRepository;
import com.example.scheduleproject_develop.user.entity.User;
import com.example.scheduleproject_develop.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentResponseDto createComment(HttpServletRequest request, Long taskId, CommentRequestDto requestDto) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(Const.LOGIN_USER_ID);

        User user = userRepository.findByIdOrElseThrow(userId);

        Task findTask = taskRepository.findByIdOrElseThrow(taskId);

        Comment comment = new Comment(requestDto.getContent(), findTask, user);
        Comment saved = commentRepository.save(comment);

        return new CommentResponseDto(saved.getId(), saved.getContent());
    }
}
