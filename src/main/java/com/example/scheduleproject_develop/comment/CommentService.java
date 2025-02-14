package com.example.scheduleproject_develop.comment;

import com.example.scheduleproject_develop.comment.dto.CommentRequestDto;
import com.example.scheduleproject_develop.comment.dto.CommentResponseDto;
import com.example.scheduleproject_develop.comment.dto.UpdateCommentRequestDto;
import com.example.scheduleproject_develop.common.Const;
import com.example.scheduleproject_develop.task.Task;
import com.example.scheduleproject_develop.task.TaskRepository;
import com.example.scheduleproject_develop.user.User;
import com.example.scheduleproject_develop.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponseDto createComment(HttpServletRequest request, Long taskId, CommentRequestDto requestDto) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(Const.LOGIN_USER_ID);

        User user = userRepository.findByIdOrElseThrow(userId);

        Task findTask = taskRepository.findByIdOrElseThrow(taskId);

        Comment comment = new Comment(requestDto.getContent(), findTask, user);
        Comment saved = commentRepository.save(comment);

        findTask.updateNumOfComments(findTask.getNumOfComments() + 1);

        return CommentResponseDto.toDto(saved);
    }

    public List<CommentResponseDto> findAllComments() {

        return commentRepository.findAll().stream().map(CommentResponseDto::toDto).toList();
    }

    @Transactional
    public void updateComment(Long id, UpdateCommentRequestDto requestDto) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        findComment.updateComment(requestDto.getContent());
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        commentRepository.delete(findComment);

    }

    public List<CommentResponseDto> findCommentsByTaskId(Long taskId) {
        List<Comment> commentsById = commentRepository.findCommentsByTask_Id(taskId);
        return commentsById.stream().map(CommentResponseDto::toDto).collect(Collectors.toList());
    }

//    public void deleteCommentByUserID(Long userId) {
//        List<Comment> commentByUserId = commentRepository.findCommentByUser_UserId(userId);
//        commentRepository.deleteAll(commentByUserId);
//
//    }

}
