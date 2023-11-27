package com.ssafy.trend_gaza.notification.service;


import com.ssafy.trend_gaza.notification.dto.NotificationResponse;
import com.ssafy.trend_gaza.notification.entity.Notification;
import java.util.List;
import java.util.Map;

public interface NotificationService {

    /* 알림 등록 */
    void register(NotificationResponse notificationResponse) throws Exception;

    /* 알림 조회 */
    List<Notification> list(String userIdTo) throws Exception;

    /* 알림 삭제 */
    void delete(Map<String, String> param) throws Exception;
}
