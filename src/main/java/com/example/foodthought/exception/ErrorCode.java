package com.example.foodthought.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND_BOOK(HttpStatus.BAD_REQUEST, "해당하는 책이 없습니다."),
    NOT_FOUND_SEARCH_TITLE_BOOK(HttpStatus.BAD_REQUEST, "검색 결과가 없습니다."),
    NOT_FOUND_SEARCH_BOOK(HttpStatus.BAD_REQUEST,"등록된 책이 없습니다."),
    NOT_FOUND_BOARD(HttpStatus.BAD_REQUEST, "해당 게시물이 없거나 삭제되었습니다."),
    NOT_FOUND_COMMENT(HttpStatus.BAD_REQUEST, "해당 댓글이 없거나 삭제되었습니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST,"해당 유저가 존재하지 않습니다."),
    NOT_FOUND_IMAGE(HttpStatus.BAD_REQUEST,"이미지는 필수 등록 항목입니다."),
    NO_PERMISSION(HttpStatus.BAD_REQUEST, "관리자 권한이 없습니다."),
    CANNOT_FOLLOW_SELF(HttpStatus.BAD_REQUEST,"본인을 팔로우 할 수 없습니다.");

    private final HttpStatus httpStatus;
    private String message;
    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public void setMessage(String message, String add) {
        this.message = add + message;
    }
}
