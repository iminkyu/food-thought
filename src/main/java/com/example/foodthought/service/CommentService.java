package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.comment.CommentRequest;
import com.example.foodthought.dto.comment.CommentResponse;
import com.example.foodthought.entity.User;

import java.util.List;

public interface CommentService {

    /**
     * 댓글 생성
     * @param boardId 게시글 Id값
     * @param parentCommentId 부모댓글 Id값
     * @param contents 댓글 내용
     * @param user 작성자
     * @return 성공시 true, 실패는 exception 처리
     */

    public ResponseDto<Boolean> createComment(Long boardId, Long parentCommentId, String contents, User user);

    /**
     * 댓글 조회 페이징 처리
     * @param boardId 게시글 Id값
     * @param page default 0
     * @param size default 3
     * @param sort 정렬기준(String)
     * @param isAsc ASC, DESC
     * @return List<CommentResponse>
     */

    public List<CommentResponse> getComment(Long boardId, int page, int size, String sort, boolean isAsc);

    /**
     * 댓글 수정
     * @param boardId 게시글 Id값
     * @param commentId 댓글 Id값
     * @param commentRequest 댓글 내용
     * @param user 작성자
     * @return 성공시 true, 실패는 exception 처리
     */

    public ResponseDto updateComment(Long boardId, Long commentId, CommentRequest commentRequest, User user);

    /**
     * 대댓글 수정
     * @param boardId 게시글 Id값
     * @param parentCommentId 부모 댓글 Id값
     * @param replyId 대댓글 Id값
     * @param commentRequest 댓글 내용
     * @param user 작성자
     * @return 성공시 true, 실패는 exception 처리
     */

    public ResponseDto updateReply(Long boardId, Long parentCommentId, Long replyId, CommentRequest commentRequest, User user);

    /**
     * 댓글 삭제
     * @param boardId 게시글 Id값
     * @param commentId 댓글 Id값
     * @param user 작성자
     */
    public void deleteComment(Long boardId, Long commentId, User user);

    /**
     * 대댓글 삭제
     * @param boardId 게시글 Id값
     * @param parentCommentId 부모 댓글 Id값
     * @param replyId 대댓글 Id 값
     * @param user 작성자
     */
    public void deleteReply(Long boardId, Long parentCommentId, Long replyId, User user);

    /**
     * 관리자 권한 댓글 삭제
     * @param boardId 게시글 Id값
     * @param commentId 부모 댓글 Id값
     * @param user 작성자
     */

    public void deleteAdminComment(Long boardId, Long commentId, User user);


    /**
     * 댓글 block 처리
     * @param boardId 게시글 Id값
     * @param commentId 부모 댓글 Id값
     * @param user 작성자
     */

    public void blockComment(Long boardId, Long commentId, User user);

    /**
     * 댓글 조회 페이징 처리
     * @param boardId 게시글 Id값
     * @param page default 0
     * @param size default 3
     * @param sort 정렬기준(String)
     * @param isAsc ASC, DESC
     * @return List<CommentResponse>
     */

    List<CommentResponse> getAllComment(Long boardId, int page, int size, String sort, boolean isAsc);
}
