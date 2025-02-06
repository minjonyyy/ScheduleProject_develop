package com.example.scheduleproject_develop.task.entity;

import com.example.scheduleproject_develop.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name="task")
public class Task extends BaseEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique=true)
    private String username;

    @Column(nullable = false)
    private String title;

    private String contents;

}
