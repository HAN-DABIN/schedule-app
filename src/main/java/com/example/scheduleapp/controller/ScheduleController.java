package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.CreateScheduleRequest;
import com.example.scheduleapp.dto.CreateScheduleResponse;
import com.example.scheduleapp.dto.GetScheduleResponse;
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
    // 스캐쥴 생성 API 기능
    @PostMapping("/schedules") // 요청 매핑
    public ResponseEntity<CreateScheduleResponse> CreateSchedule (
            @RequestBody CreateScheduleRequest request) { // DTO를 requestbody로 받아서 service로 전달
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request)); // 저장 후 성공 결과 반환
    }

    // 스케쥴 전체 조회 API 기능
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getUserSchedule (
            @RequestParam(required = false) String userName) { // userName 있으면 사용자일정 조회 없으면 전체 일정 조회
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(userName));
    }

}
