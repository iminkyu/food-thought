package com.example.foodthought.repository.follow;

import com.example.foodthought.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findFollowsByFollowing_IdAndFollower_Id(Long followingId, Long followerId);
}
