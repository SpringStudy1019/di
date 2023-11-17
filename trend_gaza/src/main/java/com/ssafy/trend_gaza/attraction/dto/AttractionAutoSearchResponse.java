package com.ssafy.trend_gaza.attraction.dto;

import lombok.Builder;

@Builder
public class AttractionAutoSearchResponse implements Comparable<AttractionAutoSearchResponse> {
	
	private int contentId;
	private String title;
	
	public AttractionAutoSearchResponse() {}

	public AttractionAutoSearchResponse(int contentId, String title) {
		super();
		this.contentId = contentId;
		this.title = title;
	}

	public int getContentId() {
		return contentId;
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public int compareTo(AttractionAutoSearchResponse o) {
		return this.getTitle().compareTo(o.getTitle());
	}

	
	
}
