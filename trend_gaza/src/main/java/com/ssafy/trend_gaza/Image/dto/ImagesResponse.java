package com.ssafy.trend_gaza.Image.dto;


import java.util.List;

public class ImagesResponse {

    private List<String> imageNames;

    public ImagesResponse() {}

    public ImagesResponse(List<String> imageNames) {
        super();
        this.imageNames = imageNames;
    }

    public List<String> getImageNames() {
        return imageNames;
    }
}
