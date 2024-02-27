package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.like.LikeTopResponseDto;
import com.example.foodthought.entity.Board;
import com.example.foodthought.entity.Like;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.BoardRepository;
import com.example.foodthought.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;


    //좋아요/좋아요 취소
    public ResponseDto toggleLike(User user, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("해당하는 게시물이 없습니다.")
        );
        Like oldLike = findLike(board.getId(), user.getId());
        if (oldLike != null) {
            likeRepository.delete(oldLike);
        } else {
            Like like = buildLike(user, board);
            likeRepository.save(like);
        }
        return ResponseDto.success(HttpStatus.NO_CONTENT.value());
    }


    public ResponseDto findBoardByLikeTop3() {
        List<Object[]> top3 = likeRepository.findBoardByLikeTop3();

        List<LikeTopResponseDto> responseDtos = new ArrayList<>();
        for (Object[] objects : top3) {
            Board board = (Board) objects[0];
            LikeTopResponseDto dto = buildLikeTop(board,(Long)objects[1]);
            responseDtos.add(dto);
        }
        return ResponseDto.success(HttpStatus.CREATED.value(), responseDtos);
    }


    //Like 존재여부 확인
    private Like findLike(Long boardId, Long userId) {
        return likeRepository.findLikesByBoard_IdAndUser_Id(boardId, userId);
    }


    //Like Entity 생성
    private Like buildLike(User user, Board board) {
        return Like.builder().user(user).board(board).build();
    }


    //LikeTopResponseDto 생성
    private LikeTopResponseDto buildLikeTop(Board board ,Long countLikes){
        return LikeTopResponseDto.builder().boardId(board.getId())
                .booktitle(board.getBooktitle())
                .bookauthor(board.getBookauthor())
                .bookpublisher(board.getBookpublisher())
                .bookcategory(board.getBookcategory())
                .bookimage(board.getBookimage())
                .username(board.getUsers().getUsername())
                .contents(board.getContents())
                .countLikes(countLikes).build();
    }
}
