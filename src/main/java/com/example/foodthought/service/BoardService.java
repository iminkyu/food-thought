package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.board.CreateBoardRequestDto;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.dto.board.UpdateBoardRequestDto;
import com.example.foodthought.entity.Board;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.BoardRepository;
import com.example.foodthought.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    public ResponseDto createBoard(CreateBoardRequestDto create, User user) {

        Board register = Board.builder().contents(create.getContents()).users(user).build();

        boardRepository.save(register);

        return ResponseDto.success(HttpStatus.CREATED.value(), "게시물이 작성되었습니다.");
    }


    public List<GetBoardResponseDto> totalInquiry(GetBoardResponseDto total, User user) {








//        게시판은 게시글의 목록과 게시글의 상세를 볼 수 있어야 함
//        게시글 목록은 제목과 작성자 작성 시간의 간략화된 정보를 보여 줌
//        게시글 상세는 제목, 작성자, 내용, 작성시간, 수정 시간 등의 상세한 정보를 보여줘야 함
        return null;
    }


    public GetBoardResponseDto oneInquiry(Long boardId, GetBoardResponseDto one, User user) {
        Board board = boardRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException());
//        게시판은 게시글의 목록과 게시글의 상세를 볼 수 있어야 함
//        게시글 목록은 제목과 작성자 작성 시간의 간략화된 정보를 보여 줌
//        게시글 상세는 제목, 작성자, 내용, 작성시간, 수정 시간 등의 상세한 정보를 보여줘야 함

        return null;
    }


    @Transactional
    public ResponseDto updateBoard(Long boardId, UpdateBoardRequestDto update, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new NoSuchElementException("해당 ID의 게시물을 찾을 수 없습니다."));

        if(!board.getUsers().getId().equals(user.getId())) {
            throw new IllegalArgumentException("게시물을 수정할 수 있는 권한이 없습니다.");
        }

//        게시판의 게시글은 수정이 가능해야 함
//        게시글 수정 시 제목과 내용이 수정 가능하고, 수정 시간이 기록되어야 함
//        게시글의 제목과 내용은 최소 n글자 이상이어야 함
//        게시글 수정은 작성자만 가능해야 함

        return ResponseDto.success(HttpStatus.CREATED.value(), "게시물이 수정되었습니다.");
    }


    @Transactional
    public ResponseDto deleteBoard(Long boardId, ResponseDto delete, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () ->new IllegalArgumentException("해당 ID의 게시물을 찾을 수 없습니다."));

        if(!board.getUsers().getId().equals(user.getId())) {
            throw new IllegalArgumentException("게시물을 삭제할 수 있는 권한이 없습니다.");
        }

        boardRepository.deleteById(boardId);
        return ResponseDto.success(HttpStatus.NO_CONTENT.value(), "게시물이 삭제되었습니다.");
    }


}