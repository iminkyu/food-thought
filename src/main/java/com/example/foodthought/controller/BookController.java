package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.book.CreateBookRequestDto;
import com.example.foodthought.dto.book.UpdateBookRequestDto;
import com.example.foodthought.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //책 전체조회
    @GetMapping("/allbook")
    public ResponseEntity<ResponseDto> getAllBook() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBook());
    }


    //책 단권조회
    @GetMapping("/{bookId}")
    public ResponseEntity<ResponseDto> getBook(@PathVariable Long bookId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBook(bookId));
    }


    //책 입력
    @PostMapping
    public ResponseEntity<ResponseDto> createBook(
            @RequestPart CreateBookRequestDto createBookRequestDto,
            @RequestPart(value = "bookImage", required = false) MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(
                createBookRequestDto, file));
    }


    //책 수정
    @PutMapping("/{bookId}")
    public ResponseEntity<ResponseDto> updateBook(
            @PathVariable Long bookId,
            @RequestPart UpdateBookRequestDto updateBookRequestDto,
            @RequestPart(value = "bookImage", required = false) MultipartFile file) throws IOException {
        bookService.updateBook(bookId, updateBookRequestDto, file);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    //책 삭제
    @DeleteMapping("/{bookId}")
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
