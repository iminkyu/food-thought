package com.example.foodthought.service;

import com.example.foodthought.dto.admin.GetUsersResponseDto;
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
    public List<GetUsersResponseDto> findAllUser(){
         return userService.findAllUser()
                 .stream()
                 .map(user -> new GetUsersResponseDto(user))
                 .toList();
    }

    public GetUsersResponseDto findUser(Long userId){
        return new GetUsersResponseDto(userService.findUser(userId));
    }

    @Transactional
    public void deleteUser(Long userId){
        userService.deleteUser(userId);
    }
    //board

    //comment

    //book

}