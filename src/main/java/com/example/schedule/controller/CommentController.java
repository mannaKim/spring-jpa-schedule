package com.example.schedule.controller;

import com.example.schedule.dto.comment.CommentDetailResponseDto;
import com.example.schedule.dto.comment.CommentResponseDto;
import com.example.schedule.dto.comment.CommentUpdateRequestDto;
import com.example.schedule.dto.comment.CommnetCreateRequestDto;
import com.example.schedule.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CommentDetailResponseDto>> getComments(){
        List<CommentDetailResponseDto> commentDetailResponseDtoList = commentService.getComments();

        return new ResponseEntity<>(commentDetailResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDetailResponseDto> getCommentById(@PathVariable Long id){
        CommentDetailResponseDto commentDetailResponseDto = commentService.getCommentById(id);

        return new ResponseEntity<>(commentDetailResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id,
            @RequestBody CommentUpdateRequestDto requestDto
    ) {
        CommentResponseDto commentResponseDto = commentService.updateComment(id, requestDto.getContents());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
