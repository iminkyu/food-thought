package com.example.foodthought.dto.board;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateBoardRequestDto {
    public Long bookId;
    public String contents;
}
