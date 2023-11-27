package com.ssafy.trend_gaza.review.dto;


import com.ssafy.trend_gaza.review.entity.FileInfo;
import java.util.List;
import lombok.Builder;

@Builder
public class ReviewRegisterRequest {

    private String score;
    private String title;
    private String content;
    private String companion;
    private String startDate;
    private String endDate;
    private String userId;
    private Integer contentId;
    private List<FileInfo> fileInfos;

    public ReviewRegisterRequest() {}

    public ReviewRegisterRequest(
            String score,
            String title,
            String content,
            String companion,
            String startDate,
            String endDate,
            String userId,
            Integer contentId,
            List<FileInfo> fileInfos) {
        super();
        this.score = score;
        this.title = title;
        this.content = content;
        this.companion = companion;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.contentId = contentId;
        this.fileInfos = fileInfos;
    }

    public String getScore() {
        return score;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCompanion() {
        return companion;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public List<FileInfo> getFileInfos() {
        return fileInfos;
    }
}
