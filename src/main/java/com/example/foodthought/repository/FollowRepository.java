package com.example.foodthought.repository;

import com.example.foodthought.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findFollowsByFollowing_IdAndFollower_Id(Long followingId, Long followerId);

    @Query(value = "SELECT f.follower, COUNT(f) FROM Follow f GROUP BY f.follower ORDER BY COUNT(f) DESC LIMIT 3")
    List<Object[]> findFollowerByLikeTop3();
}
