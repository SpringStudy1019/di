package com.ssafy.trend_gaza.review.dto;


import lombok.Builder;

@Builder
public class ReviewModifyResponse {

    private Integer reviewIdx;
    private String score;
    private String title;
    private String content;
    private String companion;
    private String startDate;
    private String endDate;

    public ReviewModifyResponse() {}

    public ReviewModifyResponse(
            Integer reviewIdx,
            String score,
            String title,
            String content,
            String companion,
            String startDate,
            String endDate) {
        super();
        this.reviewIdx = reviewIdx;
        this.score = score;
        this.title = title;
        this.content = content;
        this.companion = companion;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getReviewIdx() {
        return reviewIdx;
    }

    public String getScore() {
        return score;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCompanion() {
        return companion;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "ReviewModifyRequest [reviewIdx="
                + reviewIdx
                + ", score="
                + score
                + ", title="
                + title
                + ", content="
                + content
                + ", companion="
                + companion
                + ", startDate="
                + startDate
                + ", endDate="
                + endDate
                + "]";
    }
}
