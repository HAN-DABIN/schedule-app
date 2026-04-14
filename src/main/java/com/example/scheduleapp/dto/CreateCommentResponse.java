package com.example.scheduleapp.dto;

import java.time.LocalDateTime;

public class CreateCommentResponse {

        // 속성
        private final Long id;
        private final String contents;
        private final String userName;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;

        // 생성자
        public CreateCommentResponse(Long id, String contents, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
            this.id = id;
            this.contents = contents;
            this.userName = userName;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
        }

        // 기능
        public Long getId() {
            return id;
        }

        public String getContents() {
            return contents;
        }

        public String getUserName() {
            return userName;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public LocalDateTime getModifiedAt() {
            return modifiedAt;
        }
}
