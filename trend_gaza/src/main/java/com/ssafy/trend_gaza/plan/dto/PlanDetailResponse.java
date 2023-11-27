package com.ssafy.trend_gaza.plan.dto;


import com.ssafy.trend_gaza.plan.entity.Attraction;
import com.ssafy.trend_gaza.user.entity.User;
import java.util.List;

public class PlanDetailResponse {

    private Integer planIdx;
    private String title;
    private List<Attraction> attractions;
    private String startDate;
    private String endDate;
    private List<User> participants;

    public PlanDetailResponse() {}

    public PlanDetailResponse(
            Integer planIdx,
            List<Attraction> attractions,
            String title,
            String startDate,
            String endDate,
            List<User> participants) {
        super();
        this.planIdx = planIdx;
        this.attractions = attractions;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = participants;
    }

    public Integer getPlanIdx() {
        return planIdx;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<User> getParticipants() {
        return participants;
    }
}
