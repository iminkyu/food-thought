package com.example.foodthought.repository;

import com.example.foodthought.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
//    List<Board> findAllByUser(User user);

}

