package com.example.scheduleproject_develop.task.repository;

import com.example.scheduleproject_develop.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface TaskRepository extends JpaRepository<Task, Long> {

    default Task findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = "+id));
    }

}
