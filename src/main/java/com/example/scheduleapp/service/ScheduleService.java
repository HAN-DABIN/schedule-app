package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.*;
import com.example.scheduleapp.entity.Schedule;
import com.example.scheduleapp.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    // 속
    private final ScheduleRepository scheduleRepository;


    // 생
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 저장
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        // Request DTO를 Entity로 변환
        Schedule schedule = new Schedule(request.getTitle(), request.getContents(), request.getUserName(), request.getPassword());
        Schedule savedSchedule =  scheduleRepository.save(schedule); // DB저장
        // Entity를 Response DTO로 변환 후 반환
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getUserName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 전체 조회
    @Transactional(readOnly = true) // 조회만
    public List<GetScheduleResponse> findAll(String userName) {
        List<Schedule> schedules;
        // userName이 없으면 전체 조회 있으면 userName 일정만 조회
        if (userName == null) {
            schedules = scheduleRepository.findAll();
        } else {
            schedules = scheduleRepository.findByUserName(userName);
        }

        // Entity를 Response DTO로 변환
        List<GetScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getUserName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 단 건 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        // Id 조회하고 없으면 예외처리
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정을 찾을 수 없습니다.")
        );
        // 조회 결과 반환
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 수정
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        // 수정 일정 조회
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("수정할 일정이 없습니다.")
        );
        // 비밀번호 검증
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("비밀번호가 올바르지 않습니다.");
        }

        // 제목 수정
        schedule.updateSchedule(request.getTitle(), request.getUserName());
        // 수정 결과 반환
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long scheduleId, String password) {
        // 삭제 일정 조회 (없으면 예외 발생)
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("삭제할 일정이 없습니다.")
        );
        // id에 저장된 password 비교 (다르면 예외 발생)
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalStateException("비밀번호가 올바르지 않습니다.");
        }
        // password가 올바를 시 삭제
        scheduleRepository.deleteById(scheduleId);
    }
}
