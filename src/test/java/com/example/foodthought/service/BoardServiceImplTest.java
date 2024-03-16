package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.board.CreateBoardRequestDto;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.dto.board.UpdateBoardRequestDto;
import com.example.foodthought.dto.book.CreateBookRequestDto;
import com.example.foodthought.entity.*;
import com.example.foodthought.repository.BoardRepository;
import com.example.foodthought.repository.BookRepository;
import com.example.foodthought.repository.CommentRepository;
import com.example.foodthought.repository.LikeRepository;
import com.example.foodthought.service.BoardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.foodthought.exception.ErrorCode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class BoardServiceImplTest {

    @Mock
    BoardRepository boardRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    LikeRepository likeRepository;
    @Mock
    CommentRepository commentRepository;
    @InjectMocks
    BoardServiceImpl boardService;

    Board testBoard;
    User testUser;
    Book testBook;

//    @Test
//    @DisplayName("게시물 작성 - 성공")
//    void test1() {
//        User testUser = mock(User.class);
//        when(testUser.getId()).thenReturn(1L);
//        when(testUser.getUsername()).thenReturn("username");
//        Book testBook = mock(Book.class);
//        when(testBook.getId()).thenReturn(1L);
//        when(testBook.getTitle()).thenReturn("테스트1");
//        Board testBoard = mock(Board.class);
//        CreateBoardRequestDto createBoardRequestDto = mock(CreateBoardRequestDto.class);
//        when(boardRepository.save(any(Board.class))).thenReturn(testBoard);
//        ResponseDto<Void> responseDto = boardService.createBoard(createBoardRequestDto, testUser);
//        verify(boardRepository, times(1)).save(any(Board.class));
//    }

}