package com.example.foodthought.dto.board;


import lombok.*;

@Getter
@Builder
public class GetBoardResponseDto {
    private String title;
    private String author;
    private String publisher;
    private String image;
    private String category;
    private String contents;
}

