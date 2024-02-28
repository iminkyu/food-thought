package com.example.foodthought.service;

import com.example.foodthought.dto.comment.CommentResponse;
import com.example.foodthought.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;
    private final BoardService boardService;
    private final CommentService commentService;
    private final BookService bookService;
    private final PasswordEncoder passwordEncoder;

    //user
    public List<User> findAllUser() {
        return userService.findAllUser();
    }

    public User findUser(Long userId) {
        return userService.findUser(userId);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }
    //board

    //comment


    @Transactional
    public void deleteAdminComment(Long boardId, Long commentId, User user) {
        commentService.deleteAdminComment(boardId, commentId, user);
    }

    @Transactional
    public void blockComment(Long boardId, Long commentId, User user) {
        commentService.blockComment(boardId, commentId, user);
    }

    public List<CommentResponse> getAllComment(Long boardId) {
        return commentService.getAllComment(boardId);
    }

    //book

}