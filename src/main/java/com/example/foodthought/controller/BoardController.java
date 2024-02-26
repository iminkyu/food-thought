package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.board.CreateBoardRequestDto;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.dto.board.UpdateBoardRequestDto;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;


    @PostMapping
    public ResponseEntity<ResponseDto> createBoard(@RequestBody CreateBoardRequestDto create,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.createBoard(create, userDetails.getUser()));

    }


    @GetMapping
    public ResponseEntity<GetBoardResponseDto> totalInquiry(GetBoardResponseDto total,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.totalInquiry(total, userDetails.getUser()));
    }


    @GetMapping("/{boardId}")
    public ResponseEntity<GetBoardResponseDto> oneInquiry(@PathVariable Long boardId,
                                                          GetBoardResponseDto one,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.oneInquiry(boardId, one, userDetails.getUser()));
    }


    @PutMapping("/{boardId}")
    public ResponseEntity<ResponseDto> updateBoard(@PathVariable Long boardId,
                                                   @RequestBody UpdateBoardRequestDto update,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(boardService.updateBoard(boardId, update, userDetails.getUser()));
    }


    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable Long boardId, ResponseDto delete,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(boardService.deleteBoard(boardId, delete, userDetails.getUser()));
    }
}
