package com.example.foodthought.controller;

import com.example.foodthought.common.ResponseDto;
import com.example.foodthought.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/likes")
public class LikesController {

    private LikesService likesService;


    @PostMapping
    public ResponseDto toggleLikes(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long boardId){
        return likesService.toggleLikes(userDetails.getUser(), boardId);
    }
}
