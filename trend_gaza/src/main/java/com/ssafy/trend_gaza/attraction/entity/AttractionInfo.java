package com.ssafy.trend_gaza.attraction.entity;

public class AttractionInfo {
    private Integer contentId;
    private Integer contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private Integer readcount;
    private Integer sidoCode;
    private Integer gugunCode;
    private Double latitude;
    private Double longitude;
    private String mlevel;
    private Integer hit;
    private Double distance;
    private Integer currentPage;
    private Integer totalPageCount;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(Integer contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getFirstImage2() {
        return firstImage2;
    }

    public void setFirstImage2(String firstImage2) {
        this.firstImage2 = firstImage2;
    }

    public Integer getReadcount() {
        return readcount;
    }

    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }

    public Integer getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(Integer sidoCode) {
        this.sidoCode = sidoCode;
    }

    public Integer getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(Integer gugunCode) {
        this.gugunCode = gugunCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMlevel() {
        return mlevel;
    }

    public void setMlevel(String mlevel) {
        this.mlevel = mlevel;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    @Override
    public String toString() {
        return "AttractionInfoDto [contentId="
                + contentId
                + ", contentTypeId="
                + contentTypeId
                + ", title="
                + title
                + ", addr1="
                + addr1
                + ", addr2="
                + addr2
                + ", zipcode="
                + zipcode
                + ", tel="
                + tel
                + ", firstImage="
                + firstImage
                + ", firstImage2="
                + firstImage2
                + ", readcount="
                + readcount
                + ", sidoCode="
                + sidoCode
                + ", gugunCode="
                + gugunCode
                + ", latitude="
                + latitude
                + ", longitude="
                + longitude
                + ", mlevel="
                + mlevel
                + ", hit="
                + hit
                + ", distance="
                + distance
                + "]";
    }
}
