package com.example.foodthought.dto.board;


import lombok.*;

@Getter
@Builder
public class UpdateBoardRequestDto {
    private Long bookId;
    private String contents;
}
