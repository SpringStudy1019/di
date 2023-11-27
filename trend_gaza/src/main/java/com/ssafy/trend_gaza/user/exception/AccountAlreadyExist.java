package com.ssafy.trend_gaza.user.exception;


import com.ssafy.trend_gaza.common.CustomException;
import com.ssafy.trend_gaza.common.CustomExceptionStatus;

public class AccountAlreadyExist extends CustomException {

    public static final CustomException EXCEPTION = new AccountAlreadyExist();

    private AccountAlreadyExist() {
        super(CustomExceptionStatus.ACCOUNT_ALREADY_EXIST);
    }
}
