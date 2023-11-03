package com.ssafy.trend_gaza.question.dto;

public class QuestionUpdateRequest {
	
	int questionId;
	String content;
	
	
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
	
	
	@Override
	public String toString() {
		return "QuestionUpdateRequest [questionId=" + questionId + ", content=" + content + "]";
	}
	
	
}
