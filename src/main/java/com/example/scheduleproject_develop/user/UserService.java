package com.example.scheduleproject_develop.user;

import com.example.scheduleproject_develop.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;



    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    public UserResponseDto findUserById(Long userId) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        return new UserResponseDto(findUser.getUserId(), findUser.getUsername());
    }

    @Transactional
    public void updateUser(Long userId, String username, String password) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        findUser.updateUser(username,password);
    }

    @Transactional
    public void deleteUserById(Long userId) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        userRepository.delete(findUser);
    }


}
