package com.example.foodthought.dto.board;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBoardResponseDto {
    private String booktitle;
    private String bookauthor;
    private String bookpublisher;
    private String bookimage;
    private String bookcategory;
    private String contents;
}

