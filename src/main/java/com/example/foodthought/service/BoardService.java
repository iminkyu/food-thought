package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.board.CreateBoardRequestDto;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.dto.board.UpdateBoardRequestDto;
import com.example.foodthought.entity.Board;
import com.example.foodthought.entity.Book;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.BoardRepository;
import com.example.foodthought.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BookRepository bookRepository;


    //게시물 생성
    @Transactional
    public ResponseDto createBoard(CreateBoardRequestDto create, User user) {
        Book book = findBook(create.getBookId());
        boardRepository.save(convertToBoard(create, user));
        return ResponseDto.success(201);
    }


    //게시물 모두조회
    public ResponseDto getAllBoards(int page, int size, String sort, boolean isAsc) {
        if (boardRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("등록된 게시물이 없습니다.");
        }
        PageRequest pageRequest = PageRequest.of(page, size, !isAsc ? Sort.by(sort).descending() : Sort.by(sort).ascending());
        Page<Board> boards = boardRepository.findAll(pageRequest);
        return ResponseDto.success(200, convertToDtoList(boards));
    }


    //게시물 단건조회
    public ResponseDto getBoard(Long boardId) {
        Board board = findBoard(boardId);
        return ResponseDto.success(200, convertToDto(board));
    }


    //게시물 수정
    @Transactional
    public void updateBoard(Long boardId, UpdateBoardRequestDto updateBoardRequestDto, User user) {
        Board board = findBoard(boardId);
        if (!isMyBoard(board, user)) {
            throw new IllegalArgumentException("게시물을 수정 할 수 있는 권한이 없습니다.");
        }
        Book book = findBook(updateBoardRequestDto.getBookId());
        board.update(updateBoardRequestDto, user);
        boardRepository.save(board);
    }


    //게시물 삭제
    @Transactional
    public void deleteBoard(Long boardId, User user) {
        Board board = findBoard(boardId);
        if (!isMyBoard(board, user)) {
            throw new IllegalArgumentException("게시물을 삭제할 수 있는 권한이 없습니다.");
        }
        boardRepository.delete(board);
//        board.deleteUpdate();
//        List<Comment> delcomment = commentRepository.찾아와;
//        for (Comment comment : delcomment) {
//            comment.deleteUpdate();
//        }
    }


    //게시물 찾기
    private Board findBoard(Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물을 찾을 수 없습니다."));
//        if(board.status(block)){
//            throw new IllegalArgumentException("삭제된 게시물입니다.");
//        }
        return board;
    }


    private Book findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new IllegalArgumentException("해당 도서를 찾을 수 없습니다."));
    }


    //본인게시물인지 확인
    private boolean isMyBoard(Board board, User user) {
        return board.getUser().getId().equals(user.getId());
    }


    //Board -> GetBoardResponseDto
    private GetBoardResponseDto convertToDto(Board board) {
        Book book = findBook(board.getBookId());
        return GetBoardResponseDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .image(book.getImage())
                .category(book.getCategory())
                .contents(board.getContents()).build();
    }


    //boards -> GetBoardResponseDtos
    private List<GetBoardResponseDto> convertToDtoList(Page<Board> boards) {
        List<GetBoardResponseDto> getBoardResponseDtos = new ArrayList<>();
        for (Board board : boards) {
            Book book = findBook(board.getBookId());
            GetBoardResponseDto dto = GetBoardResponseDto.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .publisher(book.getPublisher())
                    .image(book.getImage())
                    .category(book.getCategory())
                    .contents(board.getContents()).build();
            getBoardResponseDtos.add(dto);
        }
        return getBoardResponseDtos;
    }


    //book+user+dto -> board
    private Board convertToBoard(CreateBoardRequestDto dto, User user) {
        return Board.builder()
                .bookId(dto.getBookId())
                .user(user)
                .contents(dto.getContents()).build();
    }

}