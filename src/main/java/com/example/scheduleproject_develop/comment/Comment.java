package com.example.scheduleproject_develop.comment;

import com.example.scheduleproject_develop.common.BaseEntity;
import com.example.scheduleproject_develop.task.Task;
import com.example.scheduleproject_develop.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Table(name="comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne @JoinColumn(name="task_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_task", value = ConstraintMode.CONSTRAINT))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;

    @ManyToOne @JoinColumn(name="user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_user", value = ConstraintMode.CONSTRAINT))
    @OnDelete(action = OnDeleteAction.CASCADE)
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
