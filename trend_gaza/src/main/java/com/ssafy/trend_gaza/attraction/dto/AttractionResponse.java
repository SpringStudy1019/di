package com.ssafy.trend_gaza.attraction.dto;

import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;

public class AttractionResponse {
	
	private String imgUrl;
	private String title;
	private Integer contentTypeId;
	private Integer reviewCnt;
	private String address;
	private Float reviewScore;
	

	private AttractionResponse(String imgUrl, String title, Integer contentTypeId, Integer reviewCnt, String address,
			Float reviewScore) {
		super();
		this.imgUrl = imgUrl;
		this.title = title;
		this.contentTypeId = contentTypeId;
		this.reviewCnt = reviewCnt;
		this.address = address;
		this.reviewScore = reviewScore;
	}


	public static AttractionResponse of(final AttractionInfo attraction, final Integer reviewCnt, final Float reviewScore) {
		return new AttractionResponse(attraction.getFirstImage(), attraction.getTitle(),
				attraction.getContentTypeId(), reviewCnt, attraction.getAddr1(), reviewScore);
	}
	
}
