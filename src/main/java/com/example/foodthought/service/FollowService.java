package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.entity.Follow;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.UserRepository;
import com.example.foodthought.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;


    //팔로우/취소
    @Transactional
    public ResponseDto toggleFollow(User user, Long followerId) {
        User follower = userRepository.findById(followerId).orElseThrow(() ->
                new IllegalArgumentException("해당하는 유저가 없습니다.")
        );
        Follow oldFollow = findFollow(user.getId(), follower.getId());
        if (oldFollow != null) {
            followRepository.delete(oldFollow);
        } else {
            Follow follow = buildEntity(user, follower);
            followRepository.save(follow);
        }
        return ResponseDto.success(HttpStatus.NO_CONTENT.value());
    }


    //팔로우 되어있는지 확인
    private Follow findFollow(Long followingId, Long followerId) {
        return followRepository.findFollowsByFollowing_IdAndFollower_Id(followingId, followerId);
    }


    //팔로우 Entity 생성
    private Follow buildEntity(User following, User follower) {
        return Follow.builder().following(following).follower(follower).build();
    }
}
