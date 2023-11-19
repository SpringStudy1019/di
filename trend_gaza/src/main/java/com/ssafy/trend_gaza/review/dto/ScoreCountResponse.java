package com.ssafy.trend_gaza.review.dto;

import lombok.Builder;

@Builder
public class ScoreCountResponse {

	private int contentId;
    private int scoreOneCount;
    private int scoreTwoCount;
    private int scoreThreeCount;
    private int scoreFourCount;
    private int scoreFiveCount;
    
    public ScoreCountResponse() {}
    
	public ScoreCountResponse(int contentId, int scoreOneCount, int scoreTwoCount, int scoreThreeCount,
			int scoreFourCount, int scoreFiveCount) {
		super();
		this.contentId = contentId;
		this.scoreOneCount = scoreOneCount;
		this.scoreTwoCount = scoreTwoCount;
		this.scoreThreeCount = scoreThreeCount;
		this.scoreFourCount = scoreFourCount;
		this.scoreFiveCount = scoreFiveCount;
	}

	
	public int getContentId() {
		return contentId;
	}

	public int getScoreOneCount() {
		return scoreOneCount;
	}

	public int getScoreTwoCount() {
		return scoreTwoCount;
	}

	public int getScoreThreeCount() {
		return scoreThreeCount;
	}

	public int getScoreFourCount() {
		return scoreFourCount;
	}

	public int getScoreFiveCount() {
		return scoreFiveCount;
	}  
    
}
