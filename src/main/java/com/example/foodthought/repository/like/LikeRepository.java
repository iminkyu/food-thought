package com.example.foodthought.repository.like;

import com.example.foodthought.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean findLikesByBoards_IdAndUsers_IdEquals(Long boardId, Long userId);
}
