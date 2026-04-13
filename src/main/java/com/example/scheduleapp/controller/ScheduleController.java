package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.*;
import com.example.scheduleapp.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 컴포넌트 스캔
@RequestMapping
public class ScheduleController {
    // 속
    private final ScheduleService service;

    // 생
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    // 기
    // 일정 생성
    @PostMapping("/schedules") // 요청 매핑
    public ResponseEntity<CreateScheduleResponse> CreateSchedule (
            @RequestBody CreateScheduleRequest request) { // DTO를 requestbody로 받아서 service로 전달
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request)); // 저장 후 성공 결과 반환
    }

    // 일정 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule (
            @RequestParam(required = false) String userName) { // userName 있으면 사용자일정 조회 없으면 전체 일정 조회
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(userName));
    }

    // 일정 단 건 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOneSchedule (
            @PathVariable Long scheduleId) { // service에 id넘기기
        return ResponseEntity.status(HttpStatus.OK).body(service.findOne(scheduleId));
    }

    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule (
            @PathVariable Long scheduleId,
            @RequestBody UpdateTitleScheduleRequest request
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateTitle(scheduleId,request));
    }

}
