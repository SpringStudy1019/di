package com.ssafy.trend_gaza.user.entity;


import com.ssafy.trend_gaza.user.exception.PlatformNotFoundExcption;
import java.util.Arrays;

public enum PlatformType {
    GOOGLE("google"),
    NAVER("naver"),
    KAKAO("kakao");

    private final String platform;

    private PlatformType(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public static PlatformType of(String source) {
        return Arrays.stream(PlatformType.values())
                .filter(platform -> platform.toString().contentEquals(source))
                .findAny()
                .orElseThrow(() -> PlatformNotFoundExcption.EXCEPTION);
    }
}
