package com.ssafy.trend_gaza.bookmark.entity;

import lombok.Builder;

@Builder
public class Bookmark {
	
	private Integer bookmarkIdx;
	private String userId;
	private Integer contentId;
	
	
	public Bookmark() {}


	public Bookmark(Integer bookmarkIdx, String userId, Integer contentId) {
		super();
		this.bookmarkIdx = bookmarkIdx;
		this.userId = userId;
		this.contentId = contentId;
	}


	public Integer getBookmarkIdx() {
		return bookmarkIdx;
	}


	public String getUserId() {
		return userId;
	}


	public Integer getContentId() {
		return contentId;
	}
	
}
