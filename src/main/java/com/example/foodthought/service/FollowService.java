package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.follow.FollowTopResponseDto;
import com.example.foodthought.entity.User;

import java.util.List;

public interface FollowService {
    /**
     * 팔로우 기능
     * @param user 팔로우 하는 유저
     * @param userId 유저 Id
     */

    public void toggleFollow(User user, Long userId);

    /**
     * 팔로우가 많은 3명의 유저 출력
     * @return 상태코드와 함께 팔로우가 많은 3명의 유저 출력
     */
    public ResponseDto<List<FollowTopResponseDto>> findFollowerByLikeTop3();
}
