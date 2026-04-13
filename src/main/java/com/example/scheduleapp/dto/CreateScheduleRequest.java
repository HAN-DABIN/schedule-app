package com.example.scheduleapp.dto;

public class CreateScheduleRequest {
    // 속성
    private String title;
    private String contents;
    private String userName;
    private String password;

    // 생성자

    // 기능
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
