package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    //user

    @GetMapping()
    public ResponseEntity test(){
        return ResponseEntity.ok().build();
    }
    //board

    //comment
    @PutMapping("/api/boards/{boardId}/comments/{commentId}/block")
    public ResponseEntity<ResponseDto> blockComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(adminService.blockComment(
                boardId, commentId, userDetails.getUser()));
    }

    @DeleteMapping("/api/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<String> deleteAdminComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        adminService.deleteAdminComment(boardId,commentId,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //book
}