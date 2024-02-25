package com.example.foodthought.service.follow;

import com.example.foodthought.common.ResponseDto;
import com.example.foodthought.entity.Follow;
import com.example.foodthought.repository.follow.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private FollowRepository followRepository;
    private UserService userService;


    //팔로우/취소
    public ResponseDto addFollow(User user, Long followerId) {
        //팔로워 가져오기
//        Users follower = usersService.유저검증메소드(followerId);
        Follow follow = buildEntity(user, follower);
        if (isFollow(user.getId(), follower.getId())) {
            followRepository.delete(follow);
            return new ResponseDto(200, follower.getName() + "님을 팔로우 취소했습니다");
        } else {
            followRepository.save(follow);
            return new ResponseDto(200, follower.getName() + "님을 팔로우 했습니다.");
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
