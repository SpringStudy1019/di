package com.ssafy.trend_gaza.attraction.dto;


import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import java.util.List;
import lombok.Builder;

/*
 * 여행 계획 세우기 페이지에서 검색 요청 응답 DTO
 */
@Builder
public class AttractionSearchResponse {

    private List<AttractionInfo> attractions;
    private Integer currentPage;
    private Integer totalPageCount;

    public AttractionSearchResponse() {}

    public AttractionSearchResponse(
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
