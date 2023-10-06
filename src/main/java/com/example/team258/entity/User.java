package com.example.team258.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends Timestamped{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(orphanRemoval = true,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Survey> surveys = new ArrayList<>();

    @OneToMany(orphanRemoval = true,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Answer> answers = new ArrayList<>();

    public User(String username, String password, UserRoleEnum role) {
        this.username=username;
        this.password=password;
        this.role=role;
    }

    public void addSurvey(Survey survey) {
        this.surveys.add(survey);
    }
}
