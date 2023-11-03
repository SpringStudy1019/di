package com.ssafy.trend_gaza.question.entity;

import java.time.LocalDateTime;

public class Question {
	
	private int questionId;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String userId;
	
	
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
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	@Override
	public String toString() {
		return "Review [questionId=" + questionId + ", content=" + content + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", userId=" + userId + "]";
	}
	
}
