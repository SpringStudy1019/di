package com.ssafy.trend_gaza.common;

public class Response<T> extends CommonResponse {

    private T result;

    public Response() {
        super();
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
