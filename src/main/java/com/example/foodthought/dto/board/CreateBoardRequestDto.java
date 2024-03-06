package com.example.foodthought.dto.board;

import lombok.*;

import java.awt.print.Book;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class CreateBoardRequestDto {
    private Book book;
    private String contents;
}
