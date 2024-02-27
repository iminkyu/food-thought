package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class LikeController {

    private final LikeService likeService;


    @PostMapping("/{boardId}/likes")
    public ResponseEntity<ResponseDto> toggleLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long boardId) {
        return ResponseEntity.status(204).body(likeService.toggleLike(userDetails.getUser(), boardId));
    }


    //가장 Like 많은 게시물 3개
    @GetMapping("/likes-top3")
    public ResponseEntity<ResponseDto> findBoardByLikeTop3() {
        return ResponseEntity.status(201).body(likeService.findBoardByLikeTop3());
    }
}
