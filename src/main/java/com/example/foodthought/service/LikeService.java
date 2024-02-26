package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.entity.Board;
import com.example.foodthought.entity.Like;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.BoardRepository;
import com.example.foodthought.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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


    //Like 존재여부 확인
    private Like findLike(Long boardId, Long userId) {
        return likeRepository.findLikesByBoard_IdAndUser_Id(boardId, userId);
    }


    //Like Entity 생성
    private Like buildLike(User user, Board board) {
        return Like.builder().user(user).board(board).build();
    }
}
