package com.example.foodthought.dto.board;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class CreateBoardRequestDto {
    private String title;
    private String author;
    private String publisher;
    private String image;
    private String category;
    private String contents;
}
