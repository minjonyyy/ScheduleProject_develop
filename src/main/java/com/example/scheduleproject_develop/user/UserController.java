package com.example.scheduleproject_develop.user;

import com.example.scheduleproject_develop.auth.AuthService;
import com.example.scheduleproject_develop.comment.CommentService;
import com.example.scheduleproject_develop.task.TaskService;
import com.example.scheduleproject_develop.user.dto.UserRequestDto;
import com.example.scheduleproject_develop.user.dto.UserResponseDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TaskService taskService;
    private final CommentService commentService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> allUsersDto = userService.findAllUsers();
        return new ResponseEntity<>(allUsersDto, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long userId){
        UserResponseDto userByIdDto = userService.findUserById(userId);
        return new ResponseEntity<>(userByIdDto, HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDto requestDto){
        userService.updateUser(userId, requestDto.getUsername(), requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId, HttpServletRequest request, HttpServletResponse response){
//        taskService.deleteTasksByUserId(userId);
//        commentService.deleteCommentByUserID(userId);
        userService.deleteUserById(userId);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //삭제 성공
    }

}
