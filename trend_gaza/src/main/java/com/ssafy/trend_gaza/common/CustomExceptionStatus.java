package com.ssafy.trend_gaza.common;

public enum CustomExceptionStatus {
	
	SUCCESS(true, 1000, "요청에 성공하였습니다."),
	
	EXCEED_IMAGE_CAPACITY(false, 2001, "업로드 가능한 이미지 용량을 초과했습니다."),
  NULL_IMAGE(false, 2002, "업로드한 이미지 파일이 NULL입니다."),
  EMPTY_IMAGE_LIST(false, 2003, "최소 한 장 이상의 이미지를 업로드해야합니다."),
  EXCEED_IMAGE_LIST_SIZE(false, 2004, "업로드 가능한 이미지 개수를 초과했습니다."),
  INVALID_IMAGE_URL(false, 2005, "요청한 이미지 URL의 형식이 잘못되었습니다."),
  INVALID_IMAGE_PATH(false, 2101, "이미지를 저장할 경로가 올바르지 않습니다."),
  FAIL_IMAGE_NAME_HASH(false, 2102, "이미지 이름을 해싱하는 데 실패했습니다."),
  INVALID_IMAGE(false, 2103, "올바르지 않은 이미지 파일입니다."),
	FILE_CONVERT_FAIL(false, 2104, "변환할 수 없는 파일입니다."),

	SCORE_NOT_FOUND(false, 3001, "리뷰 점수가 올바른 타입이 아닙니다."),
	COMPANION_NOT_FOUND(false, 3002, "여행 동행자 타입이 존재하지 않습니다.");

	
	private final boolean isSuccess;
    private final int code;
    private final String message;
	
    
    private CustomExceptionStatus(boolean isSuccess, int code, String message) {
		this.isSuccess = isSuccess;
		this.code = code;
		this.message = message;
	}
	
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	
}
