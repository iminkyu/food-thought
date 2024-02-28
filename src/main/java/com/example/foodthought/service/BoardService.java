package com.example.foodthought.service;

import com.example.foodthought.dto.board.CreateBoardRequestDto;
import com.example.foodthought.dto.board.GetBoardResponseDto;
import com.example.foodthought.dto.board.UpdateBoardRequestDto;
import com.example.foodthought.entity.Board;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.BoardRepository;
import com.example.foodthought.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    @Transactional
    public void createBoard(CreateBoardRequestDto create, User user) {

        Board register = Board.builder().contents(create.getContents()).user(user).build();

        boardRepository.save(register);
    }


    public GetBoardResponseDto getAllBoards() {
        // title, author, publisher, image, category, contents

      return null;

    }


    public GetBoardResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return null;

    }

    //private List<>


    @Transactional
    public void updateBoard(Long boardId, UpdateBoardRequestDto update, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당 ID의 게시물을 찾을 수 없습니다."));

        if (!board.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("게시물을 수정할 수 있는 권한이 없습니다.");
        }
    }


    @Transactional
    public void deleteBoard(Long boardId, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당 ID의 게시물을 찾을 수 없습니다."));

        if (!board.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("게시물을 삭제할 수 있는 권한이 없습니다.");
        }
        boardRepository.deleteById(boardId);
    }

}