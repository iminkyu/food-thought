package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.follow.FollowTopResponseDto;
import com.example.foodthought.entity.Follow;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.FollowRepository;
import com.example.foodthought.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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


    public ResponseDto findFollowerByLikeTop3() {
        List<Object[]> top3 = followRepository.findFollowerByLikeTop3();

        List<FollowTopResponseDto> responseDtos = new ArrayList<>();
        for (Object[] objects : top3) {
            User user = (User) objects[0];
            FollowTopResponseDto dto = buildFollowTop(user,(Long)objects[1]);
            responseDtos.add(dto);
        }
        return ResponseDto.success(HttpStatus.CREATED.value(), responseDtos);
    }


    //팔로우 되어있는지 확인
    private Follow findFollow(Long followingId, Long followerId) {
        if(followingId.equals(followerId)) throw new IllegalArgumentException("본인을 Follow 할 수 없습니다.");

        return followRepository.findFollowsByFollowing_IdAndFollower_Id(followingId, followerId);
    }


    //팔로우 Entity 생성
    private Follow buildEntity(User following, User follower) {
        return Follow.builder().following(following).follower(follower).build();
    }


    //FollowTopResponseDto 생성
    private FollowTopResponseDto buildFollowTop(User user ,Long contFollows){
        return FollowTopResponseDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .username(user.getUsername())
                .intro(user.getIntro())
                .userPhoto(user.getUserPhoto())
                .countFollows(contFollows).build();
    }
}
