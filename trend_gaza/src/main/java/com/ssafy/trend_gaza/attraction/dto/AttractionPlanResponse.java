package com.ssafy.trend_gaza.attraction.dto;


import lombok.Builder;

@Builder
public class AttractionPlanResponse {

    private String contentId;
    private Integer contentTypeId;
    private String title;
    private String address;
    private String imageUrl;
    private Double latitude;
    private Double longitude;

    public AttractionPlanResponse() {}

    public AttractionPlanResponse(
            String contentId,
            Integer contentTypeId,
            String title,
            String address,
            String imageUrl,
            Double latitude,
            Double longitude) {
        super();
        this.contentId = contentId;
        this.contentTypeId = contentTypeId;
        this.title = title;
        this.address = address;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getContentId() {
        return contentId;
    }

    public Integer getContentTypeId() {
        return contentTypeId;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
