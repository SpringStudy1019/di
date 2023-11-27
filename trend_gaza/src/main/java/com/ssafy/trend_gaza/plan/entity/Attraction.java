package com.ssafy.trend_gaza.plan.entity;

/*
 * 사용자가 세운 여행 계획
 */
public class Attraction {

    private Integer attractionId;
    private Integer orderDate; // 방문일자 n일차
    private Integer order; // 방문 순서

    public Attraction() {}

    public Attraction(Integer attractionId, Integer orderDate, Integer order) {
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
