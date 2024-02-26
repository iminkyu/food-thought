package com.example.foodthought.dto.comment;

import com.example.foodthought.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private String contents;
    private String userId;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public CommentResponse(Comment comment) {
        this.contents = comment.getContents();
        this.userId = comment.getUser().getUserId();
        this.createAt = comment.getCreateAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
