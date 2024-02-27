package com.example.foodthought.service;

import lombok.RequiredArgsConstructor;
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

    //book

}