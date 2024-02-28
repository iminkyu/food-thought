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

import java.util.ArrayList;
import java.util.List;

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


    public List<GetBoardResponseDto> getAllBoards() {
        List<Board> boardList = boardRepository.findAll();

        List<GetBoardResponseDto> getBoardResponseDtoList = new ArrayList<>();

        if (boardList.isEmpty()) {
            throw new IllegalArgumentException("게시물을 확인할 수 없습니다.");
        }
        return getBoardResponseDtoList;
    }


    public List<GetBoardResponseDto> getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        List<GetBoardResponseDto> getBoard = new ArrayList<>();

        if (!board.getUser().getId().equals(boardId)) {
            throw new IllegalArgumentException("사용자의 id와 게시물의 id가 일치하지 않습니다.");
        }

        return getBoard;

    }


    @Transactional
    public void updateBoard(Long boardId, UpdateBoardRequestDto update, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당 ID의 게시물을 찾을 수 없습니다."));

        if (!board.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("게시물을 수정할 수 있는 권한이 없습니다.");
        }
        if (!board.getContents().equals(update.getContents())) {
            throw new IllegalArgumentException("게시물 수정할 수 없습니다.");

        }
        boardRepository.save(board);
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