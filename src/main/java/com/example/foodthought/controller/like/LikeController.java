package com.example.foodthought.controller.like;

import com.example.foodthought.common.ResponseDto;
import com.example.foodthought.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/likes")
public class LikeController {

    private LikeService likeService;


    @PostMapping
    public ResponseDto toggleLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long boardId){
        return likeService.toggleLike(userDetails.getUser(), boardId);
    }
}
