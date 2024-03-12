package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.board.CreateBoardRequestDto;
import com.example.foodthought.dto.board.UpdateBoardRequestDto;
import com.example.foodthought.entity.User;

public interface BoardService {
    /**
     * 게시물 생성
     * @param create 게시물 생성 시 받는 값
     * @param user 게시물 생성한 유저
     * @return 상태 코드 출력, 실패시 exception 처리
     */
    public ResponseDto createBoard(CreateBoardRequestDto create, User user);

    /**
     * 모든 게시물 조회
     * @param page 게시물 생성 시 받는 값
     * @param size 게시물 생성한 유저
     * @param sort  정렬 기준
     * @param isASC ASC, DESC
     * @return 상태 코드와 함께 모든 게시글 정보 출력 실패는 exception 처리
     */
    public ResponseDto getAllBoards(int page, int size, String sort, boolean isASC);

    /**
     * 게시물 단건 조회
     * @param boardId 게시물 Id
     * @return 상태 코드와 함께 게시물 정보 출력 실패는 exception 처리
     */
    public ResponseDto getBoard(Long boardId);

    /**
     * 게시물 수정
     * @param boardId 게시물 Id
     * @param update 수정 시 입력 받는 정보
     * @param user 유저 정보
     */
    void updateBoard(Long boardId, UpdateBoardRequestDto update, User user);

    /**
     * 게시물 삭제
     * @param boardId 게시물 Id
     * @param user 유저 정보
     */
    void deleteBoard(Long boardId, User user);
}
