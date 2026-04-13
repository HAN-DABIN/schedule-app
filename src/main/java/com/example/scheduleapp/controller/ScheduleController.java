package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.CreateScheduleRequest;
import com.example.scheduleapp.dto.CreateScheduleResponse;
import com.example.scheduleapp.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CreateScheduleResponse> CreateSchedule (@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));


    }
}
