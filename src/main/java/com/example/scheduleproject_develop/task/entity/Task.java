package com.example.scheduleproject_develop.task.entity;

import com.example.scheduleproject_develop.common.entity.BaseEntity;
import com.example.scheduleproject_develop.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="task")
public class Task extends BaseEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    private String contents;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public Task() {
    }

    public Task(String username, String title, String contents, User user) {
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    //setter
    public void updateTask(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
