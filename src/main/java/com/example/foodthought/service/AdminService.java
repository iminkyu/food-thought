package com.example.foodthought.service;

import com.example.foodthought.dto.admin.GetUsersResponseDto;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.dto.book.CreateBookRequestDto;
import com.example.foodthought.dto.book.UpdateBookRequestDto;
import com.example.foodthought.dto.comment.CommentResponse;
import com.example.foodthought.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Transactional
    public void deleteAdminBoard(Long boardId) {
        boardService.deleteAdminBoard(boardId);
    }


    @Transactional
    public void blockBoard(Long boardId) {
        boardService.blockBoard(boardId);
    }


    public List<GetBoardResponseDto> getAdminAllBoard(int page, int size, String sort, boolean isAsc) {
        return boardService.getAdminAllBoard(page, size, sort, isAsc);
    }


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


    // book
    public void createBook(CreateBookRequestDto createBookRequestDto, MultipartFile file) throws IOException {
        bookService.createBook(createBookRequestDto, file);
    }


    public void updateBook(Long bookId, UpdateBookRequestDto updateBookRequestDto, MultipartFile file) throws IOException {
        bookService.updateBook(bookId, updateBookRequestDto, file);
    }


    public void deleteBook(Long bookId) {
        bookService.deleteBook(bookId);
    }

}