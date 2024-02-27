package com.example.foodthought.dto.board;


import lombok.*;

import java.awt.print.Book;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBoardRequestDto {
    private Book book;
    private String contents;
}
