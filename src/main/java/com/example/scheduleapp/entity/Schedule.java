package com.example.scheduleapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@jakarta.persistence.Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {

    // 속
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "schedule")
    private List<Comment> comments = new ArrayList<>();

    // 생
    protected Schedule() {}

    public Schedule(String title, String contents, String userName, String password) {
        this.title = title;
        this.contents = contents;
        this.userName = userName;
        this.password = password;
    }

    public void updateSchedule(String title, String userName) {
        this.title = title;
        this.userName = userName;
    }

    // 기
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
