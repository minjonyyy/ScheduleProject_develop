package com.example.scheduleproject_develop.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface TaskRepository extends JpaRepository<Task, Long> {

    default Task findByIdOrElseThrow(Long taskId) {
        return findById(taskId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = "+taskId));
    }

    Page<Task> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
