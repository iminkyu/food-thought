package com.example.foodthought.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreatCommentRequest {
    private Long parentCommentId;
    @NotBlank(message = "수정할 댓글 내용을 입력 해주세요.")
    private String contents;
}
