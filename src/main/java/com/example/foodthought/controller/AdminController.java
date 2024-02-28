package com.example.foodthought.controller;

import com.example.foodthought.dto.comment.CommentResponse;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    //user

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAllUser());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity getUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findUser(userId));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //board


    //comment
    @PutMapping("/api/boards/{boardId}/comments/{commentId}/block")
    public ResponseEntity blockComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        adminService.blockComment(boardId, commentId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/api/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<String> deleteAdminComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        adminService.deleteAdminComment(boardId, commentId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/api/boards/{boardId}/comments")
    public List<CommentResponse> getAllComment(
            @PathVariable Long boardId) {
        return adminService.getAllComment(boardId);
    }


    //book
}