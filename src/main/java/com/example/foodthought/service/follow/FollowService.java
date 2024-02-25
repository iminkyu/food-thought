package com.example.foodthought.service.follow;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.entity.Follow;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.UserRepository;
import com.example.foodthought.repository.follow.FollowRepository;
import com.example.foodthought.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private FollowRepository followRepository;
    private UserRepository userRepository;


    //팔로우/취소
    public ResponseDto addFollow(User user, Long followerId) {
        User follower = userRepository.findById(followerId).orElseThrow(() ->
                new IllegalArgumentException("해당하는 유저가 없습니다.")
        );
        Follow follow = buildEntity(user, follower);
        if (isFollow(user.getId(), follower.getId())) {
            followRepository.delete(follow);
            return new ResponseDto(200, follower.getUsername() + "님을 팔로우 취소했습니다");
        } else {
            followRepository.save(follow);
            return new ResponseDto(200, follower.getUsername() + "님을 팔로우 했습니다.");
        }
    }


    //팔로우 되어있는지 확인
    private boolean isFollow(Long followingId, Long followerId) {
        return followRepository.findFollowsByFollowing_IdAndFollower_IdEquals(followingId, followerId);
    }


    //팔로우 Entity 생성
    private Follow buildEntity(User following, User follower) {
        return Follow.builder().following(following).follower(follower).build();
    }
}
