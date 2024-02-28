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

//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(createBoard(create.getContents(), create.getBook()),userDetails.getUser());
        return null;
    }


    @GetMapping
    public ResponseEntity<GetBoardResponseDto> getAllBoards() {

        boardService.getAllBoards();
//        return ResponseEntity.status(HttpStatus.OK).;
        return null;
    }


    @GetMapping("/{boardId}")
    public ResponseEntity<GetBoardResponseDto> getBoard(@PathVariable Long boardId) {

//        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(boardId));
        return null;
    }


    @PutMapping("/{boardId}")
    public ResponseEntity<Void> updateBoard(@PathVariable Long boardId,
                                            @RequestBody UpdateBoardRequestDto update,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return null;
    }


    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        boardService.deleteBoard(boardId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
