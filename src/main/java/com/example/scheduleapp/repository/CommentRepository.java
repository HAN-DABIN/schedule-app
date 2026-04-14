package com.example.scheduleapp.repository;

import com.example.scheduleapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 일정(scheduleId)에 달린 댓글 개수를 조회하는 메서드
    long countByScheduleId(Long scheduleId);
}
