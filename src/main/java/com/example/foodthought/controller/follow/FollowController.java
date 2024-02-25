package com.example.foodthought.controller.follow;

import com.example.foodthought.common.ResponseDto;
import com.example.foodthought.service.follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/follows")
public class FollowController {
    private FollowService followService;


    //팔로잉/취소
    @PostMapping
    public ResponseDto addFollow(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long userId) {
        return followService.addFollow(userDetails.getUser(), followerId);
    }
}
