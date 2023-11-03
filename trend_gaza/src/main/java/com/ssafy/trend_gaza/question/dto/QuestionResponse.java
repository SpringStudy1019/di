package com.ssafy.trend_gaza.question.dto;

import java.time.LocalDateTime;

public class QuestionResponse {
	
	int questionId;
	String content;
	LocalDateTime createdAt;
	//List<AnswerResponse> answers;
	int answerCnt;
	String userId;
	String userImg;
	
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public int getAnswerCnt() {
		return answerCnt;
	}
	public void setAnswerCnt(int answerCnt) {
		this.answerCnt = answerCnt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	
	
	@Override
	public String toString() {
		return "QuestionResponse [questionId=" + questionId + ", content=" + content + ", createdAt=" + createdAt
				+ ", answerCnt=" + answerCnt + ", userId=" + userId + ", userImg=" + userImg + "]";
	}
	
}
