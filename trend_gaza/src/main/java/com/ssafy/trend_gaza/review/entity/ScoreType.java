package com.ssafy.trend_gaza.review.entity;


import com.ssafy.trend_gaza.review.exception.ScoreNotFoundException;
import java.util.Arrays;

public enum ScoreType {
    ONE("ONE"),
    TWO("TWO"),
    THREE("THREE"),
    FOUR("FOUR"),
    FIVE("FIVE");

    private final String value;

    private ScoreType(String value) {
        this.value = value;
    }

    public static ScoreType of(String source) {
        return Arrays.stream(ScoreType.values())
                .filter(score -> score.toString().contentEquals(source))
                .findAny()
                .orElseThrow(() -> ScoreNotFoundException.EXCEPTION);
    }

    public String getValue() {
        return value;
    }
}
