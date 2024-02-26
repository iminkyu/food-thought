package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.comment.CommentRequest;
import com.example.foodthought.dto.comment.CommentResponse;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class CommentController {
    private final CommentService commentService;


    // 댓글 생성
    @PostMapping("/{boardId}/comments")
    public ResponseEntity<ResponseDto> createComment(
            @PathVariable Long boardId,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(boardId, request, userDetails.getUser()));
    }


    // 댓글 조회
    @GetMapping("/{boardId}/comments")
    public List<CommentResponse> getCommentByBoard(
            @PathVariable Long boardId) {
        return commentService.getCommentByBoard(boardId);
    }


    // 댓글 수정
    @PutMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @RequestBody CommentRequest commentRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.updateComment(boardId, commentId, commentRequest, userDetails.getUser()));
    }

    @DeleteMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(boardId, commentId, userDetails.getUser());
        return ResponseEntity.ok("댓글 삭제 완료");
    }

}
