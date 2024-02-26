package com.example.foodthought.repository;

import com.example.foodthought.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findLikesByBoard_IdAndUser_Id(Long boardId, Long userId);
}
