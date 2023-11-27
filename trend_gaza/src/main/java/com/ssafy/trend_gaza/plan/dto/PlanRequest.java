package com.ssafy.trend_gaza.plan.dto;


import lombok.Builder;

@Builder
public class PlanRequest {

    private Integer attractionId;
    private Integer orderDate; // 방문일자 n일차
    private Integer order; // 방문 순서

    public PlanRequest() {}

    public PlanRequest(Integer attractionId, Integer orderDate, Integer order) {
        super();
        this.attractionId = attractionId;
        this.orderDate = orderDate;
        this.order = order;
    }

    public Integer getAttractionId() {
        return attractionId;
    }

    public Integer getOrderDate() {
        return orderDate;
    }

    public Integer getOrder() {
        return order;
    }
}
