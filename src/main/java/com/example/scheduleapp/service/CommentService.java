package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.CreateCommentRequest;
import com.example.scheduleapp.dto.CreateCommentResponse;
import com.example.scheduleapp.entity.Comment;
import com.example.scheduleapp.entity.Schedule;
import com.example.scheduleapp.repository.CommentRepository;
import com.example.scheduleapp.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    // 속
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;


    // 생
    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public CreateCommentResponse save(Long scheduleId, CreateCommentRequest request) {
        // scheduleId에 해당하는 일정이 없으면 예외 발생
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("일정이 없습니다."));
        // 댓글이 10개 이상이면 작성 불가
        long count = commentRepository.countByScheduleId(scheduleId);
        if (count >= 10) {
            throw new IllegalStateException("댓글은 최대 10개까지 가능합니다.");
        }
        // Request DTO를 Entity로 변환
        Comment comment = new Comment(request.getContents(), request.getUserName(), request.getPassword());
        comment.setSchedule(schedule); // 연관관계 설정
        Comment savedComment =  commentRepository.save(comment); // DB저장
        // Entity를 Response DTO로 변환 후 반환
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContents(),
                savedComment.getUserName(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }
}
