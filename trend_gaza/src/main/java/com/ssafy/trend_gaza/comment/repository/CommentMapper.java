package com.ssafy.trend_gaza.comment.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.comment.dto.CommentModifyResponse;
import com.ssafy.trend_gaza.comment.dto.CommentRegisterRequest;
import com.ssafy.trend_gaza.comment.entity.Comment;

@Mapper
public interface CommentMapper {
	
	/* 댓글 등록 */ 
	void register(CommentRegisterRequest registerRequest) throws SQLException;
	
	/* 댓글 전체 조회 */
	List<Comment> list() throws SQLException;
	
	/* 댓글 수정 */
	CommentModifyResponse getModify(int commentIdx) throws SQLException;
	void modify(CommentModifyResponse modifyResponse) throws SQLException;
	
	/* 댓글 삭제 */ 
	void delete(String commentIdx) throws SQLException;
}
