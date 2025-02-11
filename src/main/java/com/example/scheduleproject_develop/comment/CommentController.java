package com.example.scheduleproject_develop.comment;

import com.example.scheduleproject_develop.comment.dto.CommentRequestDto;
import com.example.scheduleproject_develop.comment.dto.CommentResponseDto;
import com.example.scheduleproject_develop.comment.dto.UpdateCommentRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAllComments(){
        List<CommentResponseDto> allComments = commentService.findAllComments();
        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable Long id, @RequestBody UpdateCommentRequestDto requestDto){
        commentService.updateComment(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        commentService.deleteCommet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
