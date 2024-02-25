package com.example.foodthought.dto.board;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class CreateBoardRequestDto {
    private String booktitle;
    private String bookauthor;
    private String bookpublisher;
    private String bookimage;
    private String bookcategory;
    private String contents;

    CreateBoardRequestDto createBoardRequestDto = CreateBoardRequestDto.builder()
            .booktitle(booktitle)
            .bookauthor(bookauthor)
            .bookpublisher(bookpublisher)
            .bookimage(bookimage)
            .bookcategory(bookcategory)
            .contents(contents)
            .build();
}
