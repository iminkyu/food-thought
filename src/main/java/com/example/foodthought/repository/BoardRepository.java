package com.example.foodthought.repository;

import com.example.foodthought.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long>, PagingAndSortingRepository<Board, Long>, BoardRepositoryCustom {
    Page<Board> findAll(Pageable pageable);
    List<Board> findAll();

}

