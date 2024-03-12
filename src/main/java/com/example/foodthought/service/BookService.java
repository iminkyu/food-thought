package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.book.CreateBookRequestDto;
import com.example.foodthought.dto.book.GetBookResponseDto;
import com.example.foodthought.dto.book.UpdateBookRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    /**
     * 모든 책 조회
     * @param page default 0
     * @param size default 3
     * @param sort 정렬기준(String)
     * @param isASC ASC, DESC
     * @param title 책 제목
     * @return 상태 코드와 함께 모든 책 정보 출력 실패는 exception 처리
     */
    public ResponseDto<List<GetBookResponseDto>> getAllBook(int page, int size, String sort, boolean isASC, String title);

    /**
     * 책 단건 조회
     * @param bookId 게시글 Id값
     * @return 상태 코드와 함께 책 정보 출력 실패는 exception 처리
     */
    public ResponseDto<GetBookResponseDto> getBook(Long bookId);

    /**
     * 책 입력
     * @param createBookRequestDto 책 입력 정보 값
     * @param file 책 사진
     * @return 상태 코드 출력 실패는 exception 처리
     */
    public ResponseDto<Void> createBook(CreateBookRequestDto createBookRequestDto, MultipartFile file) throws IOException;

    /**
     * 책 입력
     * @param bookId 책 입력 정보 값
     * @param updateBookRequestDto 책 수정 입력 정보 값
     * @param file 책 사진
     */
    public void updateBook(Long bookId, UpdateBookRequestDto updateBookRequestDto, MultipartFile file) throws IOException;

    /**
     * 책 입력
     * @param bookId 책 입력 정보 값
     */
    public void deleteBook(Long bookId);
}
