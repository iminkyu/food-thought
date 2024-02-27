package com.example.foodthought.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequestDto {
    private String title;
    private String author;
    private String publisher;
    private String category;
}
