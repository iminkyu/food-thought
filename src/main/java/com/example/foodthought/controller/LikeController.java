package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/likes")
public class LikeController {

    private final LikeService likeService;


    @PostMapping
    public ResponseEntity<ResponseDto> toggleLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long boardId){
        return ResponseEntity.status(204).body(likeService.toggleLike(userDetails.getUser(), boardId));
    }
}
