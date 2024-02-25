package com.example.foodthought.service;

import com.example.foodthought.common.ResponseDto;
import com.example.foodthought.entity.Boards;
import com.example.foodthought.entity.Likes;
import com.example.foodthought.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {
    private LikesRepository likesRepository;
    private BoardSerivce boardSerivce;


    //좋아요/좋아요 취소
    public ResponseDto toggleLikes(Users users, Long boardId) {
        Boards boards = boardService.findBoard(boardId);
        Likes likes = Likes.builder().users(users).boards(boards).build();

        if (isLikes(likes.getUsers().getId(), likes.getBoards().getId())) {
            likesRepository.delete(likes);
            return new ResponseDto(200, "좋아요 취소");
        } else {
            likesRepository.save(likes);
            return new ResponseDto(200, "좋아요");
        }
    }


    //Likes 존재여부 확인
    private boolean isLikes(Long boardId, Long userId) {
        return likesRepository.findLikesByBoard_IdAndUser_IdEquals(boardId, userId);
    }
}
