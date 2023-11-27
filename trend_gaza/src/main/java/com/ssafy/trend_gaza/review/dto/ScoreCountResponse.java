package com.ssafy.trend_gaza.review.dto;


import lombok.Builder;

@Builder
public class ScoreCountResponse {

    private Integer contentId;
    private Integer scoreOneCount;
    private Integer scoreTwoCount;
    private Integer scoreThreeCount;
    private Integer scoreFourCount;
    private Integer scoreFiveCount;

    public ScoreCountResponse() {}

    public ScoreCountResponse(
            Integer contentId,
            Integer scoreOneCount,
            Integer scoreTwoCount,
            Integer scoreThreeCount,
            Integer scoreFourCount,
            Integer scoreFiveCount) {
        super();
        this.contentId = contentId;
        this.scoreOneCount = scoreOneCount;
        this.scoreTwoCount = scoreTwoCount;
        this.scoreThreeCount = scoreThreeCount;
        this.scoreFourCount = scoreFourCount;
        this.scoreFiveCount = scoreFiveCount;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getScoreOneCount() {
        return scoreOneCount;
    }

    public Integer getScoreTwoCount() {
        return scoreTwoCount;
    }

    public Integer getScoreThreeCount() {
        return scoreThreeCount;
    }

    public Integer getScoreFourCount() {
        return scoreFourCount;
    }

    public Integer getScoreFiveCount() {
        return scoreFiveCount;
    }
}
