package com.example.scheduleproject_develop.user;

import com.example.scheduleproject_develop.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Table(name="user")
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE user_id = ?")
@SQLRestriction("deleted = false")
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void updateUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
