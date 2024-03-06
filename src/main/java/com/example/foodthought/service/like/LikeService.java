package com.example.foodthought.service.like;

import com.example.foodthought.common.ResponseDto;
import com.example.foodthought.entity.Like;
import com.example.foodthought.repository.like.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private LikeRepository likeRepository;
    private BoardSerivce boardSerivce;


    //좋아요/좋아요 취소
    public ResponseDto toggleLike(User user, Long boardId) {
        Board board = boardService.findBoard(boardId);
        Like like = buildLike(user,board);

        if (isLike(like.getUser().getId(), like.getBoard().getId())) {
            likeRepository.delete(like);
            return new ResponseDto(200, "좋아요 취소");
        } else {
            likeRepository.save(like);
            return new ResponseDto(200, "좋아요");
        }
    }


    //Like 존재여부 확인
    private boolean isLike(Long boardId, Long userId) {
        return likeRepository.findLikesByBoards_IdAndUsers_IdEquals(boardId, userId);
    }


    //Like Entity 생성
    private Like buildLike(User user, Board board){
        return Like.builder().user(user).board(board).build();
    }
}
