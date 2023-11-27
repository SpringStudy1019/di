package com.ssafy.trend_gaza.comment.service;


import com.ssafy.trend_gaza.comment.dto.CommentModifyResponse;
import com.ssafy.trend_gaza.comment.dto.CommentRegisterRequest;
import com.ssafy.trend_gaza.comment.entity.Comment;
import java.util.List;

public interface CommentService {

    /* 댓글 등록 */
    void register(CommentRegisterRequest registerRequest) throws Exception;

    /* 댓글 전체 조회 */
    List<Comment> list(int reviewIdx) throws Exception;

    /* 댓글 수정 */
    Comment getModify(int commentIdx) throws Exception;

    void modify(CommentModifyResponse modifyResponse) throws Exception;

    /* 댓글 삭제 */
    void delete(int commentIdx) throws Exception;
}
