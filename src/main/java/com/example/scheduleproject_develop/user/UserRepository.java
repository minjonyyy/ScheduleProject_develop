package com.example.scheduleproject_develop.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long userId){
        return findById(userId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id="+userId));
    }

    Optional<User> findByEmail(String email);

    default User findUserByEmailOrElseThrow(String email){
        return findByEmail(email).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 이메일과 일치하는 사용자가 없습니다."));
    }

}
