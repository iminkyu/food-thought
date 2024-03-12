package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.like.LikeTopResponseDto;
import com.example.foodthought.entity.User;

import java.util.List;

public interface LikeService {
    /**
     * like 기능
     * @param user 좋아요 누른 유저
     * @param boardId 좋아요가 등록된 게시물 Id
     */
    public void toggleLike(User user, Long boardId);

    /**
     * 좋아요 많이 받은 게시물 top3 출력
     * @return  상태코드와 함께 좋아요 많이 받은 게시물 top3 출력
     */
    public ResponseDto<List<LikeTopResponseDto>> findBoardByLikeTop3();
}
