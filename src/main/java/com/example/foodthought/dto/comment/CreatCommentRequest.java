package com.example.foodthought.dto.comment;

import lombok.Getter;

@Getter
public class CreatCommentRequest {
    private Long parentCommentId;
    private String contents;
}
