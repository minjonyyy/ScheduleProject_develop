package com.example.scheduleproject_develop.comment;

import com.example.scheduleproject_develop.comment.dto.CommentRequestDto;
import com.example.scheduleproject_develop.comment.dto.CommentResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{taskId}")
    public ResponseEntity<CommentResponseDto> createComment(HttpServletRequest request, @PathVariable Long taskId, @RequestBody CommentRequestDto requestDto){
        CommentResponseDto commentResponseDto = commentService.createComment(request, taskId, requestDto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);

    }

}
