package com.ssafy.trend_gaza.bookmark.entity;


import lombok.Builder;

@Builder
public class Bookmark {

    private Integer bookmarkIdx;
    private String userId;
    private Integer contentId;
    private String registerDate;

    public Bookmark() {}

    public Bookmark(Integer bookmarkIdx, String userId, Integer contentId, String registerDate) {
        super();
        this.bookmarkIdx = bookmarkIdx;
        this.userId = userId;
        this.contentId = contentId;
        this.registerDate = registerDate;
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

    public String getRegisterDate() {
        return registerDate;
    }
}
