package com.example.foodthought.repository.follow;

import com.example.foodthought.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean findFollowsByFollowing_IdAndFollower_IdEquals(Long followingId, Long followerId);
}
