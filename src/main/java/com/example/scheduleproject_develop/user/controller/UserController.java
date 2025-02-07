package com.example.scheduleproject_develop.user.controller;

import com.example.scheduleproject_develop.user.dto.SignupRequestDto;
import com.example.scheduleproject_develop.user.dto.UserRequestDto;
import com.example.scheduleproject_develop.user.dto.UserResponseDto;
import com.example.scheduleproject_develop.user.service.UserService;
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

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody SignupRequestDto requestDto){
        UserResponseDto userResponseDto = userService.save(requestDto.getEmail(), requestDto.getUsername(), requestDto.getPassword());
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> allUsersDto = userService.findAllUsers();
        return new ResponseEntity<>(allUsersDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id){
        UserResponseDto userByIdDto = userService.findUserById(id);
        return new ResponseEntity<>(userByIdDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto){
        userService.updateUser(id, requestDto.getUsername(), requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
