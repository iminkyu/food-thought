package com.example.foodthought.repository;

import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.entity.QBoard;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.foodthought.entity.QBoard.board;
import static com.example.foodthought.entity.QBook.book;
@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPQLQueryFactory queryFactory;

    @Override
    public Page<GetBoardResponseDto> findAllBoards(Pageable pageable) {
        List<GetBoardResponseDto> query = queryFactory
                .select(Projections.fields(GetBoardResponseDto.class,
                        book.title,
                        book.author,
                        book.publisher,
                        book.image,
                        book.category,
                        board.contents))
                .from(board)
                .orderBy(board.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = queryFactory
                .selectFrom(board)
                .fetchCount();

        return new PageImpl<>(query, pageable, totalCount);
    }
}
