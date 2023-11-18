package com.ssafy.trend_gaza.common;

public enum CustomExceptionStatus {
	
	SUCCESS(true, 1000, "요청에 성공하였습니다."),
	
	REQUEST_ERROR(false, 1100, "입력 값을 확인해 주세요."),
    EMPTY_JWT(false, 1101, "토큰이 없습니다."),
    INVALID_JWT(false, 1102, "유효하지 않은 토큰입니다."),
    INVALID_REFRESH_TOKEN(false, 1103, "유효하지 않은 RefreshToken 입니다."),
    NOT_AUTHENTICATED_ACCOUNT(false, 1104, "로그인이 필요합니다."),
    INVALID_KEY(false, 1105, "유효하지 않는 key 값입니다."),
    PLATFORM_NOT_FOUND(false, 1200, "존재하지 않는 플랫폼입니다."),
    ACCOUNT_ALREADY_EXIST(false, 1201, "다른 플랫폼에 해당 이메일로 가입된 계정이 존재합니다."),
    ACCOUNT_ACCESS_DENIED(false, 1202, "권한이 없습니다."),
    ACCOUNT_NOT_FOUND(false, 1203, "존재하지 않는 계정입니다."),
	
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
	COMPANION_NOT_FOUND(false, 3002, "여행 동행자 타입이 존재하지 않습니다."),
	
	REGISTER_BOOKMARK_FAILED(false, 4001, "북마크 추가에 실패했습니다.");

	
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
