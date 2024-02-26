package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.board.CreateBoardRequestDto;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.dto.board.UpdateBoardRequestDto;
import com.example.foodthought.dto.user.CreateUserDto;
import com.example.foodthought.entity.Board;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.BoardRepository;
import com.example.foodthought.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 게시물 작성
    public ResponseEntity<ResponseDto> createBoard(CreateBoardRequestDto create, User user) {
            //리턴타입                  //메소드      //메소드실행하기위해 받아온 값 (컨트롤러한테 받아온값)
        //1. 게시물을 저장
        //유저한테 받은 게시물의 입력값  + 로그인한유저정보를 합쳐서 게시물을 작성
        Board board = Board.builder().users(user).booktitle(create.getBooktitle()).bookauthor(create.getBookauthor())
                        .bookpublisher(create.getBookpublisher()).bookcategory(create.getBookcategory()).bookimage(create.getBookimage())
                        .contents(create.getContents()).build();
        //작성된 게시물 저장
        boardRepository.save(board);

        //2. 잘 작성됬다고 알려줄건지 << 이용자/포스트맨 각 1씩
        // 이용자한테전달할값 1 / postman전달할값 1
        ResponseDto responseDto = new ResponseDto(200,"잘 작성됬습니다."); //이용자한테 전달할값 만들었잖아요
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK); //HttpStatus.Ok < 포스트맨에 전달할값
                                                    //              이용자전달값   포스트맨전달값
        return response; //컨트롤러한테 잘 작성됬다고 내용 보내주기
    }

    // 게시물 전체 조회
    public GetBoardResponseDto totalInquiry(GetBoardResponseDto total, User user) {
        Board board = boardRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException());
        return null;
    }

    // 게시물 단건 조회
    public GetBoardResponseDto oneInquiry(Long boardid, GetBoardResponseDto one) {
        return null;
    }

    // 게시물 수정(JWTToken)
    @Transactional
    public Board updateBoard(Long boardid , UpdateBoardRequestDto update, User user) {
        Board board = boardRepository.findById(boardid).orElseThrow(
                ()-> new NoSuchElementException("해당 ID를 찾을 수 없습니다."));

   return null;
    }

    // 게시물 삭제(JWTToken)
    @Transactional
    public ResponseDto deleteBoard(ResponseDto delete) {
        return null;
    }


}