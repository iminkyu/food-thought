package com.example.foodthought.repository;

import com.example.foodthought.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    boolean findLikesByBoard_IdAndUser_IdEquals(Long boardId, Long userId);
}
