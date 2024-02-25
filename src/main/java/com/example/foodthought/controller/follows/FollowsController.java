package com.example.foodthought.controller.follows;

import com.example.foodthought.common.ResponseDto;
import com.example.foodthought.service.follows.FollowsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/follows")
public class FollowsController {
    private FollowsService followsService;


    //팔로잉/취소
    @PostMapping
    public ResponseDto addFollows(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long userId) {
        return followsService.addFollows(userDetails.getUsers(), followerId);
    }
}
