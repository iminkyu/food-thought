package com.example.foodthought.repository;


import com.example.foodthought.entity.Board;
<<<<<<< HEAD
import com.example.foodthought.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByUser(User user);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

>>>>>>> f2cb2fa (board dto, entity 구현 완료)
}

