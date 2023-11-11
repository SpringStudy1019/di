package com.ssafy.trend_gaza.review.exception;

import com.ssafy.trend_gaza.common.CustomException;
import com.ssafy.trend_gaza.common.CustomExceptionStatus;

public class ScoreNotFoundException extends CustomException {
	
	public static final CustomException EXCEPTION = new ScoreNotFoundException();
	
	private ScoreNotFoundException() {
		super(CustomExceptionStatus.SCORE_NOT_FOUND);
	}
}
