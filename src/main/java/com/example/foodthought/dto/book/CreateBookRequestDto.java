package com.example.foodthought.dto.book;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateBookRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String publisher;
    @NotNull
    private String category;
}