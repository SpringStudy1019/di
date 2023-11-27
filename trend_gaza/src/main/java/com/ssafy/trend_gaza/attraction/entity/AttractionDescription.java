package com.ssafy.trend_gaza.attraction.entity;

public class AttractionDescription {

    private Integer contentId;
    private String homepage;
    private String overview;
    private String telname;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTelname() {
        return telname;
    }

    public void setTelname(String telname) {
        this.telname = telname;
    }

    @Override
    public String toString() {
        return "AttractionDescriptionDto [contentId="
                + contentId
                + ", homepage="
                + homepage
                + ", overview="
                + overview
                + ", telname="
                + telname
                + "]";
    }
}
