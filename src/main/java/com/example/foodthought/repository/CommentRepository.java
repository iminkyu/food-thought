package com.example.foodthought.repository;

import com.example.foodthought.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, PagingAndSortingRepository<Comment, Long> {
//    List<Comment> findByBoardId(Long boardId);
    Page<Comment> findByBoardIdAndParentCommentIsNull(Long boardId, Pageable pageable);

    List<Comment> findCommentsByBoard_Id(Long boardId);
}
