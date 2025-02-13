package com.example.scheduleproject_develop.user;


import com.example.scheduleproject_develop.user.dto.UserRequestDto;
import com.example.scheduleproject_develop.user.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId, HttpServletRequest request){
        userService.deleteUserById(userId);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //삭제 성공
    }

}
