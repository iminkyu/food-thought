package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.board.CreateBoardRequestDto;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.dto.board.UpdateBoardRequestDto;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    // 게시물 작성(JWTToken)
    // 컨트롤러는 일을 하지 않는다  1. 일을 받아와서 서비스한테 시키고 서비스가 완료하면 받아온다 ==끝  2. 서비스한테 받은값을 일을 시킨사람한테 다시 준다 ==끝
    @PostMapping
    public ResponseEntity<ResponseDto> createBoard(@RequestBody CreateBoardRequestDto create, //이용자가 입력한 도서정보
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) { //로그인한 유저의 정보
                                                //서비스한테 일을 주는거예요
//        ResponseEntity<ResponseDto> response = boardService.createBoard(create,userDetails.getUser()); // 1
//        return response;
        return boardService.createBoard(create, userDetails.getUser());

    }

    // 게시물 전체 조회
    @GetMapping
    public ResponseEntity<GetBoardResponseDto> totalInquiry(GetBoardResponseDto total,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return null;
    }

    // 게시물 단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<GetBoardResponseDto> oneInquiry(@PathVariable Long boardId,
                                                          GetBoardResponseDto one,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return null;
    }

    // 게시물 수정(JWTToken)
    @PutMapping("/{boardId}")
    public ResponseEntity<ResponseDto> updateBoard(@PathVariable Long boardId,
                                                   @RequestBody UpdateBoardRequestDto update,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return null;
    }

    // 게시물 삭제(JWTToken)
    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable Long boardId, ResponseDto delete,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return null;
    }
}
