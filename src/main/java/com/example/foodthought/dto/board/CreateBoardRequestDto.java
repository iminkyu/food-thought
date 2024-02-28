package com.example.foodthought.dto.board;

import lombok.*;

import java.awt.print.Book;

@Getter
@Builder
public class CreateBoardRequestDto {
    private Long bookId;
    private String contents;
}
