package com.example.foodthought.service.follows;

import com.example.foodthought.common.ResponseDto;
import com.example.foodthought.entity.Follows;
import com.example.foodthought.repository.follows.FollowsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowsService {

    private FollowsRepository followsRepository;
    private UsersService usersService;


    //팔로우/취소
    public ResponseDto addFollows(Users users, Long followerId) {
        //팔로워 가져오기
//        Users follower = usersService.유저검증메소드(followerId);
        Follows follows = buildEntity(users, follower);
        if (isFollow(users.getId(), follower.getId())) {
            followsRepository.delete(follows);
            return new ResponseDto(200, follwer.getName() + "님을 팔로우 취소했습니다");
        } else {
            followsRepository.save(follows);
            return new ResponseDto(200, +follwer.getName() + "님을 팔로우 했습니다.");
        }
    }


    //팔로우 되어있는지 확인
    private boolean isFollow(Long followingId, Long followerId) {
        return followsRepository.findFollowsByFollowing_IdAndFollower_IdEquals(followingId, followerId);
    }


    //팔로우 Entity 생성
    private Follows buildEntity(Users following, Users follower) {
        return Follows.builder().following(following).follower(follower).build();
    }
}
