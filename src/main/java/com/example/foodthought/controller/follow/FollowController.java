package com.example.foodthought.controller.follow;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/follows")
public class FollowController {
    private final FollowService followService;


    //팔로잉/취소
    @PostMapping
    public ResponseEntity<ResponseDto> toggleFollow(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(followService.toggleFollow(userDetails.getUser(), userId));
    }
}

