package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.CreateScheduleRequest;
import com.example.scheduleapp.dto.CreateScheduleResponse;
import com.example.scheduleapp.dto.GetScheduleResponse;
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

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTitle(), request.getContents(), request.getUserName(), request.getPassword());
        Schedule savedSchedule =  scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getUserName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll(String userName) {
        List<Schedule> schedules;
        if (userName == null) {
            schedules = scheduleRepository.findAll();
        } else {
            schedules = scheduleRepository.findByUserName(userName);
        }

        List<GetScheduleResponse> result = new ArrayList<>();

        for (Schedule schedule : schedules) {
            result.add(new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getUserName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            ));
        } return result;
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정을 찾을 수 없습니다.")
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }


    // 기
    public void createSchedule(CreateScheduleRequest request) {
        String title = request.getTitle();
        String contents = request.getContents();
        String userName = request.getUserName();
        String password = request.getPassword();

        new Schedule(title, contents, userName, password);
    }
}
