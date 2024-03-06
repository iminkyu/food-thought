package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.dto.book.CreateBookRequestDto;
import com.example.foodthought.dto.book.GetBookResponseDto;
import com.example.foodthought.dto.book.UpdateBookRequestDto;
import com.example.foodthought.dto.comment.CommentResponse;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    //user

    @GetMapping("/api/users")
    public ResponseEntity getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAllUser());
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity getUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findUser(userId));
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    //board
    @DeleteMapping("/api/boards/{boardId}")
    public ResponseEntity deleteAdminBoard(@PathVariable Long boardId) {
        adminService.deleteAdminBoard(boardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/api/boards/{boardId}/block")
    public ResponseEntity updateAdminBoard(@PathVariable Long boardId) {
        adminService.blockBoard(boardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/api/boards")
    public List<GetBoardResponseDto> getAdminAllBoard(int page, int size, String sort, boolean isAsc) {
        return adminService.getAdminAllBoard(page, size, sort, isAsc);
    }



    //comment
    @PutMapping("/api/boards/{boardId}/comments/{commentId}/block")
    public ResponseEntity blockComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        adminService.blockComment(boardId, commentId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/api/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<String> deleteAdminComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        adminService.deleteAdminComment(boardId, commentId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/api/boards/{boardId}/comments")
    public List<CommentResponse> getAllAdminComment(
            @PathVariable Long boardId) {
        return adminService.getAllComment(boardId);
    }

    //book
    //책 입력
    @PostMapping("/api/books")
    public ResponseEntity<ResponseDto<List<GetBookResponseDto>>> createBook(
            @RequestPart CreateBookRequestDto createBookRequestDto,
            @RequestPart(value = "bookImage", required = true) MultipartFile file) throws IOException {
        adminService.createBook(createBookRequestDto, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    //책 수정
    @PutMapping("/api/books/{bookId}")
    public ResponseEntity<ResponseDto<Void>> updateBook(
            @PathVariable Long bookId,
            @RequestPart UpdateBookRequestDto updateBookRequestDto,
            @RequestPart(value = "bookImage", required = true) MultipartFile file) throws IOException {
        adminService.updateBook(bookId, updateBookRequestDto, file);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    //책 삭제
    @DeleteMapping("/api/books/{bookId}")
    public ResponseEntity<ResponseDto<Void>> deleteBook(@PathVariable Long bookId) {
        adminService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}