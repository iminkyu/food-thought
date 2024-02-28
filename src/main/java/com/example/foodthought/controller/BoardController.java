package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.board.CreateBoardRequestDto;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.dto.board.UpdateBoardRequestDto;
import com.example.foodthought.entity.User;
import com.example.foodthought.security.UserDetailsImpl;
import com.example.foodthought.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;


    @PostMapping
    public ResponseEntity<ResponseDto> createBoard(@RequestBody CreateBoardRequestDto create,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @GetMapping
    public ResponseEntity<GetBoardResponseDto> getAllBoards() {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.getAllBoards());
    }


    @GetMapping("/{boardId}")
    public ResponseEntity<GetBoardResponseDto> getBoard(@PathVariable Long boardId) {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(boardId));
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
