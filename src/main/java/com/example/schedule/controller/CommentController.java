package com.example.schedule.controller;

import com.example.schedule.dto.comment.CommentDetailResponseDto;
import com.example.schedule.dto.comment.CommentResponseDto;
import com.example.schedule.dto.comment.CommentUpdateRequestDto;
import com.example.schedule.dto.comment.CommnetCreateRequestDto;
import com.example.schedule.dto.common.PaginationResponse;
import com.example.schedule.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
            @Valid @RequestBody CommnetCreateRequestDto requestDto,
            HttpSession session
    ) {
        CommentResponseDto commentResponseDto = commentService.createComment(
                requestDto.getContents(),
                requestDto.getScheduleId(),
                session
        );
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<CommentDetailResponseDto>> getComments(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10, page = 0, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<CommentDetailResponseDto> commentPage = commentService.getComments(
                title,
                name,
                pageable
        );
        PaginationResponse<CommentDetailResponseDto> commentPageResponse = new PaginationResponse<>(commentPage);

        return new ResponseEntity<>(commentPageResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDetailResponseDto> getCommentById(@PathVariable Long id){
        CommentDetailResponseDto commentDetailResponseDto = commentService.getCommentById(id);

        return new ResponseEntity<>(commentDetailResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id,
            @RequestBody CommentUpdateRequestDto requestDto,
            HttpSession session
    ) {
        CommentResponseDto commentResponseDto = commentService.updateComment(
                id,
                requestDto.getContents(),
                session
        );
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, HttpSession session) {
        commentService.deleteComment(id, session);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
