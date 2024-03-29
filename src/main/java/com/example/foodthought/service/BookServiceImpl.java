package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.book.CreateBookRequestDto;
import com.example.foodthought.dto.book.GetBookResponseDto;
import com.example.foodthought.dto.book.UpdateBookRequestDto;
import com.example.foodthought.entity.Book;
import com.example.foodthought.repository.BookRepository;
import com.example.foodthought.util.StorageService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final StorageService storageService;
    private final JPAQueryFactory queryFactory;

    //책 전체 조회
    @Override
    public ResponseDto<List<GetBookResponseDto>> getAllBook(int page, int size, String sort, boolean isAsc, String title) {
        title = title.trim();
        if(title.isEmpty()){
            if (bookRepository.findAllByTitleContains(title).isEmpty()) {
                throw new IllegalArgumentException("도서명에 " + title + "이 들어가는 책이 없습니다.");
            }
        } else {
            if (bookRepository.findAll().isEmpty()) {
                throw new IllegalArgumentException("등록된 책이 없습니다.");
            }
        }
        PageRequest pageRequest = PageRequest.of(page, size, !isAsc ? Sort.by(sort).descending() : Sort.by(sort).ascending());
        Page<Book> books = bookRepository.findAllByTitleContains(pageRequest, title);
        return ResponseDto.success(200, convertToDtoList(books));
    }


    //책 단권 조회
    @Override
    @Transactional(readOnly = true)
    public ResponseDto<GetBookResponseDto> getBook(Long bookId) {
        return ResponseDto.success(200, convertToDto(findBook(bookId)));
    }


    //책 입력
    @Override
    @Transactional
    public ResponseDto<Void> createBook(CreateBookRequestDto createBookRequestDto, MultipartFile file) throws IOException {
        bookRepository.save(convertToBook(createBookRequestDto, convertToString(file)));
        return ResponseDto.success(201);
    }


    //책 수정
    @Override
    @Transactional
    public void updateBook(
            Long bookId,
            UpdateBookRequestDto updateBookRequestDto,
            MultipartFile file) throws IOException {
        Book book = findBook(bookId);
        book.update(updateBookRequestDto, convertToString(file));
        bookRepository.save(book);
    }


    //책 삭제
    @Override
    @Transactional
    public void deleteBook(Long bookId) {
        bookRepository.delete(findBook(bookId));
    }



    //책 실존유무 조회
    private Book findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(()
                -> new IllegalArgumentException("해당하는 책이 없습니다.")
        );
    }


    //책 한권을 GetBookResponseDto로 변환
    private GetBookResponseDto convertToDto(Book book) {
        return GetBookResponseDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .image(book.getImage())
                .category(book.getCategory())
                .build();
    }


    //북리스트를 builder 이용해서 GetBookResponseDto 리스트로 변환
    private List<GetBookResponseDto> convertToDtoList(Page<Book> books) {
        List<GetBookResponseDto> getBookResponseDtos = new ArrayList<>();
        for (Book book : books) {
            GetBookResponseDto dto = GetBookResponseDto.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .publisher(book.getPublisher())
                    .image(book.getImage())
                    .category(book.getCategory())
                    .createAt(book.getCreateAt())
                    .modifiedAt(book.getModifiedAt())
                    .build();
            getBookResponseDtos.add(dto);
        }
        return getBookResponseDtos;
    }


    //CreateRequestDto의 정보를 Book으로 변환
    private Book convertToBook(CreateBookRequestDto dto, String image) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publisher(dto.getPublisher())
                .image(image)
                .category(dto.getCategory())
                .build();
    }


    //file을 주소로 변환
    private String convertToString(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("이미지를 업로드 해주세요");
        }
        return storageService.uploadFile(file);
    }
}
