package com.example.foodthought.dto.board;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBoardRequestDto {
    private String booktitle;
    private String bookauthor;
    private String bookpublisher;
    private String bookimage;
    private String bookcategory;
    private String contents;

    UpdateBoardRequestDto updateBoardRequestDto = UpdateBoardRequestDto.builder()
            .booktitle(booktitle)
            .bookauthor(bookauthor)
            .bookpublisher(bookpublisher)
            .bookimage(bookimage)
            .bookcategory(bookcategory)
            .contents(contents)
            .build();

}
