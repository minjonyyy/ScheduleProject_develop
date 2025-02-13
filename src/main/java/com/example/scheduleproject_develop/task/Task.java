package com.example.scheduleproject_develop.task;

import com.example.scheduleproject_develop.common.BaseEntity;
import com.example.scheduleproject_develop.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Table(name="task")
@SQLDelete(sql = "UPDATE task SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Task extends BaseEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    private String contents;

    private Long numOfComments;

    @ManyToOne(fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="userId", nullable = true) @OnDelete(action = OnDeleteAction.SET_NULL)
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
