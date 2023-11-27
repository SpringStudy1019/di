package com.ssafy.trend_gaza.attraction.dto;


import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import java.util.List;
import lombok.Builder;

@Builder
public class AttractionCategoryResponse {

    private List<AttractionInfo> attractions;
    private Integer currentPage;
    private Integer totalPageCount;

    public AttractionCategoryResponse() {}

    public AttractionCategoryResponse(
            List<AttractionInfo> attractions, Integer currentPage, Integer totalPageCount) {
        super();
        this.attractions = attractions;
        this.currentPage = currentPage;
        this.totalPageCount = totalPageCount;
    }

    public List<AttractionInfo> getAttractions() {
        return attractions;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }
}
