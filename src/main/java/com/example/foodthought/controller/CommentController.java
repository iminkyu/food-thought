package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.comment.CommentRequest;
import com.example.foodthought.dto.comment.CommentResponse;
import com.example.foodthought.dto.comment.CreatCommentRequest;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.CommentService;
import jakarta.validation.Valid;
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


    // 댓글, 대댓글 생성
    @PostMapping("/{boardId}/comments")
    public ResponseEntity<ResponseDto> createComment(
            @PathVariable Long boardId,
            @Valid @RequestBody CreatCommentRequest creatCommentRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(
                boardId,
                creatCommentRequest.getParentCommentId(),
                creatCommentRequest.getContents(),
                userDetails.getUser()));
    }


    // 댓글 조회
    @GetMapping("/{boardId}/comments")
    public List<CommentResponse> getComments(
            @PathVariable Long boardId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "createAt") String sort,
            @RequestParam(defaultValue = "true") boolean isAsc) {
        return commentService.getComment(boardId, page, size, sort, isAsc);
    }


    // 댓글 수정
    @PutMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @RequestBody CommentRequest commentRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.updateComment(
                boardId, commentId, commentRequest, userDetails.getUser()));
    }


    // 대댓글 수정
    @PutMapping("/{boardId}/comments/{parentCommentId}/replies/{replyId}")
    public ResponseEntity<ResponseDto> updateReply(
            @PathVariable Long boardId,
            @PathVariable Long parentCommentId,
            @PathVariable Long replyId,
            @RequestBody CommentRequest commentRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.updateReply(
                boardId, parentCommentId, replyId, commentRequest, userDetails.getUser()));
    }


    // 댓글 삭제
    @DeleteMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(boardId, commentId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    // 대댓글 삭제
    @DeleteMapping("/{boardId}/comments/{parentCommentId}/replies/{replyId}")
    public ResponseEntity<String> deleteReply(
            @PathVariable Long boardId,
            @PathVariable Long parentCommentId,
            @PathVariable Long replyId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteReply(boardId, parentCommentId, replyId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
