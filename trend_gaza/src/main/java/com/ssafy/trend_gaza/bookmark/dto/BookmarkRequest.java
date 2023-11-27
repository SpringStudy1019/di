package com.ssafy.trend_gaza.bookmark.dto;

public class BookmarkRequest {

    private String userId;
    private Integer contentId;

    public BookmarkRequest() {}

    public BookmarkRequest(String userId, Integer contentId) {
        super();
        this.userId = userId;
        this.contentId = contentId;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getcontentId() {
        return contentId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
}
