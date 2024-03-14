package com.example.foodthought.repository;

import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<GetBoardResponseDto> findAllBoards(Pageable pageable);
}
