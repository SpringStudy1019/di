package com.ssafy.trend_gaza.plan.entity;


import java.util.List;

public class AttractionPlan {

    private Integer attractionPlanId;
    private Integer planIdx;
    private List<Attraction> attractions;

    public AttractionPlan() {}

    public AttractionPlan(Integer attractionPlanId, Integer planIdx, List<Attraction> attractions) {
        super();
        this.attractionPlanId = attractionPlanId;
        this.planIdx = planIdx;
        this.attractions = attractions;
    }

    public Integer getAttractionPlanId() {
        return attractionPlanId;
    }

    public Integer getPlanIdx() {
        return planIdx;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }
}
