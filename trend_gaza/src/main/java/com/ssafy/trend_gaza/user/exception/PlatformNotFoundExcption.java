package com.ssafy.trend_gaza.user.exception;

import com.ssafy.trend_gaza.common.CustomException;
import com.ssafy.trend_gaza.common.CustomExceptionStatus;

public class PlatformNotFoundExcption extends CustomException {
	
	public static final CustomException EXCEPTION = new PlatformNotFoundExcption();
	
	 private PlatformNotFoundExcption () {
	        super(CustomExceptionStatus.PLATFORM_NOT_FOUND);
	    }
}
