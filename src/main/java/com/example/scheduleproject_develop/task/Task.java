package com.example.scheduleproject_develop.task;

import com.example.scheduleproject_develop.common.BaseEntity;
import com.example.scheduleproject_develop.user.User;
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

    private Long numOfComments;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public Task() {
    }

    public Task(String username, String title, String contents, Long numOfComments, User user) {
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.numOfComments = numOfComments;
        this.user = user;
    }

    //setter
    public void updateTask(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void updateNumOfComments(Long numOfComments) {
        this.numOfComments = numOfComments;
    }
}
