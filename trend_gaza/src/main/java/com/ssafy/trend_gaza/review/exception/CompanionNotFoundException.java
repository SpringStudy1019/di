package com.ssafy.trend_gaza.review.exception;

import com.ssafy.trend_gaza.common.CustomException;
import com.ssafy.trend_gaza.common.CustomExceptionStatus;

public class CompanionNotFoundException extends CustomException {

	public static final CustomException EXCEPTION = new CompanionNotFoundException();
	
	private CompanionNotFoundException() {
		super(CustomExceptionStatus.COMPANION_NOT_FOUND);
	}
}
