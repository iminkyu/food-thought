package com.example.foodthought.repository.follows;

import com.example.foodthought.entity.Follows;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowsRepository extends JpaRepository<Follows, Long> {
    boolean findFollowsByFollowing_IdAndFollower_IdEquals(Long followingId, Long followerId);
}
