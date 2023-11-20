package com.ssafy.trend_gaza.plan.dto;

import lombok.Builder;

@Builder
public class AcceptInvitationRequest {

	private String creatorId;
	private String title;
	private String joinUserId;
	
	public AcceptInvitationRequest() {}

	public AcceptInvitationRequest(String creatorId, String title, String joinUserId) {
		super();
		this.creatorId = creatorId;
		this.title = title;
		this.joinUserId = joinUserId;
	}

	
	public String getCreatorId() {
		return creatorId;
	}

	public String getTitle() {
		return title;
	}

	public String getJoinUserId() {
		return joinUserId;
	}

}
