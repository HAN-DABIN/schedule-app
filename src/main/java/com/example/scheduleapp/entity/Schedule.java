package com.example.scheduleapp.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    // 생
    protected Schedule() {}

    public Schedule(String title, String contents, String userName, String password) {
        this.title = title;
        this.contents = contents;
        this.userName = userName;
        this.password = password;
    }

    public void updateScheduleTitle(String title) {
        this.title = title;
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
