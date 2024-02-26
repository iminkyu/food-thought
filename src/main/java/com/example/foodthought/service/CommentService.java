package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.comment.CommentRequest;
import com.example.foodthought.dto.comment.CommentResponse;
import com.example.foodthought.entity.Board;
import com.example.foodthought.entity.Comment;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.BoardRepository;
import com.example.foodthought.repository.CommentRepository;
import com.example.foodthought.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;


    // 댓글 생성
    public ResponseDto createComment(Long boardId, CommentRequest request, User user) {
        Board board = findBoard(boardId);
        User findUser = findUser(user);
        commentRepository.save(new Comment(request, board, findUser));
        return ResponseDto.success(HttpStatus.CREATED.value(), "댓글이 작성 되었습니다.");
    }


    // 댓글 조회
    public List<CommentResponse> getCommentByBoard(Long boardId) {
        Board board = findBoard(boardId);
        List<Comment> commentList = commentRepository.findByBoardBoardId(boardId);
        return convertToDtoList(commentList);
    }


    // Comment 객체를 CommentResponse 객체로 변환 후 리스트로 반환
    private List<CommentResponse> convertToDtoList(List<Comment> commentList) {
        List<CommentResponse> commentResponseList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentResponseList.add(new CommentResponse(comment));
        }
        return commentResponseList;
    }


    //유저 찾기
    private User findUser(User user) {
        return userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("없는 사용자 입니다."));
    }


    // 게시글 찾기
    private Board findBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("없는 게시글입니다."));
    }


}
