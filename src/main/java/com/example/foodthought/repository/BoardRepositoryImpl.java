package com.example.foodthought.repository;

import com.example.foodthought.config.QueryDslConfig;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.entity.QBoard;
import com.querydsl.core.types.Projections;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.foodthought.entity.QBook.book;
@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final QueryDslConfig queryDslConfig;

    @Override
    public Page<GetBoardResponseDto> findAllBoards(Pageable pageable) {
        QBoard board = QBoard.board;
        List<GetBoardResponseDto> query = queryDslConfig.jpaQueryFactory()
                .select(Projections.fields(GetBoardResponseDto.class,
                        book.title,
                        book.author,
                        book.publisher,
                        book.image,
                        book.category,
                        board.user.userId,
                        board.contents))
                .from(board)
                .orderBy(board.createAt.desc())
                .join(book).on(board.bookId.eq(book.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = queryDslConfig.jpaQueryFactory()
                .selectFrom(board)
                .fetchCount();

        return new PageImpl<>(query, pageable, totalCount);
    }
}
