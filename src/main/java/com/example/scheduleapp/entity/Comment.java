package com.example.scheduleapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

    // 속
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    private String userName;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    // 생
    protected Comment() {}

    public Comment(String contents, String userName, String password) {
        this.contents = contents;
        this.userName = userName;
        this.password = password;
    }
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    // 기
    public Long getId() {
        return id;
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
