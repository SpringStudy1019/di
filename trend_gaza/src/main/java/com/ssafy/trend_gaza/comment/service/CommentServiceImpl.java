package com.ssafy.trend_gaza.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.comment.dto.CommentModifyResponse;
import com.ssafy.trend_gaza.comment.dto.CommentRegisterRequest;
import com.ssafy.trend_gaza.comment.entity.Comment;
import com.ssafy.trend_gaza.comment.repository.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentMapper commentMapper;
	
	public CommentServiceImpl(CommentMapper commentMapper) {
		super();
		this.commentMapper = commentMapper;
	}

	@Override
	public void register(CommentRegisterRequest registerRequest) throws Exception {
		commentMapper.register(registerRequest);
	}

	@Override
	public List<Comment> list(int reviewIdx) throws Exception {
		return commentMapper.list(reviewIdx);
	}

	@Override
	public Comment getModify(int commentIdx) throws Exception {
		return commentMapper.getModify(commentIdx);
	}

	@Override
	public void modify(CommentModifyResponse modifyResponse) throws Exception {
		commentMapper.modify(modifyResponse);
		
	}

	@Override
	public void delete(int commentIdx) throws Exception {
		commentMapper.delete(commentIdx);
		
	}

}
