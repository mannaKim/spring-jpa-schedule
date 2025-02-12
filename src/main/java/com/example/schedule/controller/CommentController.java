package com.example.schedule.controller;

import com.example.schedule.dto.comment.CommentResponseDto;
import com.example.schedule.dto.comment.CommnetCreateRequestDto;
import com.example.schedule.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@Valid @RequestBody CommnetCreateRequestDto requestDto) {
        CommentResponseDto commentResponseDto = commentService.createComment(
                requestDto.getContents(),
                requestDto.getMemberId(),
                requestDto.getScheduleId()
        );
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }


}
