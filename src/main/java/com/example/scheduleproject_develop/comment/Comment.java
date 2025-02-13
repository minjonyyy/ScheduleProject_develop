package com.example.scheduleproject_develop.comment;

import com.example.scheduleproject_develop.common.BaseEntity;
import com.example.scheduleproject_develop.task.Task;
import com.example.scheduleproject_develop.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Table(name="comment")
@SQLDelete(sql = "UPDATE comment SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="task_id", nullable = true) @OnDelete(action = OnDeleteAction.SET_NULL)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="user_id", nullable = true) @OnDelete(action = OnDeleteAction.SET_NULL)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Comment(String content, Task task, User user) {
        this.content = content;
        this.task = task;
        this.user = user;
    }

    public Comment() {

    }

    public void updateComment(String content) {
        this.content = content;
    }
}
