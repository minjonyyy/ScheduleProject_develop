package com.example.scheduleproject_develop.user.service;

import com.example.scheduleproject_develop.user.dto.UserResponseDto;
import com.example.scheduleproject_develop.user.entity.User;
import com.example.scheduleproject_develop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto save(String username, String password) {
        User user = new User(username, password);
        User createdUser = userRepository.save(user);

        return new UserResponseDto(createdUser.getId(), createdUser.getUsername());
    }

    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    public UserResponseDto findUserById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findUser.getId(), findUser.getUsername());
    }

    @Transactional
    public void updateUser(Long id, String username, String password) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.updateUser(username,password);
    }

    public void deleteUserById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }


}
