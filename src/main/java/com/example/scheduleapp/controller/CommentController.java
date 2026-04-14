package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.CreateCommentRequest;
import com.example.scheduleapp.dto.CreateCommentResponse;
import com.example.scheduleapp.dto.CreateScheduleRequest;
import com.example.scheduleapp.dto.CreateScheduleResponse;
import com.example.scheduleapp.service.CommentService;
import com.example.scheduleapp.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CommentController {
    // 속
    private final CommentService service;

    // 생
    public CommentController(CommentService service) {
        this.service = service;
    }

    // 기
    // 댓글 생성
    @PostMapping("/schedules/{scheduleId}/comment") // 요청 매핑
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long scheduleId, // service에 scheduleId 전달
            @RequestBody CreateCommentRequest request) { // DTO를 requestbody로 받아서 service로 전달
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(scheduleId, request)); // 저장 후 성공 결과 반환
    }
}
