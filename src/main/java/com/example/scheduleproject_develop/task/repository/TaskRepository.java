package com.example.scheduleproject_develop.task.repository;

import com.example.scheduleproject_develop.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
