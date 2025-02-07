package com.example.scheduleproject_develop.task.entity;

import com.example.scheduleproject_develop.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="task")
public class Task extends BaseEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    private String contents;

    public Task() {
    }

    public Task(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
