package com.ssafy.trend_gaza.plan.dto;

import java.time.LocalDate;

import lombok.Builder;

/*
 * 저장한 여행 계획 DTO
 */
@Builder
public class SelectAttractionPlanResponse {
	
	private String title;
	private String imgUrl;
	private String address;
	private Integer contentId;
	private Integer order;			// 방문 순서
	private Integer orderDate;		// 방문일자
	private LocalDate startDate;
	private LocalDate endDate;
	
	
	public SelectAttractionPlanResponse() {}

	public SelectAttractionPlanResponse(String title, String imgUrl, String address, Integer contentId, Integer order,
			Integer orderDate, LocalDate startDate, LocalDate endDate) {
		super();
		this.title = title;
		this.imgUrl = imgUrl;
		this.address = address;
		this.contentId = contentId;
		this.order = order;
		this.orderDate = orderDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public String getAddress() {
		return address;
	}

	public Integer getContentId() {
		return contentId;
	}

	public Integer getOrder() {
		return order;
	}

	public Integer getOrderDate() {
		return orderDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}
	
	
	
	
}
