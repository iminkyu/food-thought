package com.example.foodthought.dto.board;

import lombok.*;

import java.awt.print.Book;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class CreateBoardRequestDto {
    private Book book;
    private String contents;
}
