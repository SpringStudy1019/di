package com.ssafy.trend_gaza.user.exception;


import com.ssafy.trend_gaza.common.CustomException;
import com.ssafy.trend_gaza.common.CustomExceptionStatus;

public class UserNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(CustomExceptionStatus.ACCOUNT_NOT_FOUND);
    }
}
