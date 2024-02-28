package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.comment.CommentRequest;
import com.example.foodthought.entity.Comment;
import com.example.foodthought.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;
    private final BoardService boardService;
    private final CommentService commentService;
    private final BookService bookService;
    private final PasswordEncoder passwordEncoder;




    //user

    //board

    //comment
    public ResponseDto blockComment(Long boardId, Long commentId,  User user) {
        commentService.blockComment(boardId, commentId, user);
        return ResponseDto.success(HttpStatus.NO_CONTENT.value());
    }

    public void deleteAdminComment(Long boardId, Long commentId, User user) {
        commentService.deleteAdminComment(boardId, commentId, user);
    }
    //book

}