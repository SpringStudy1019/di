package com.ssafy.trend_gaza.notification.service;

import java.util.List;

import com.ssafy.trend_gaza.notification.dto.NotificationResponse;
import com.ssafy.trend_gaza.notification.entity.Notification;

public interface NotificationService {

	/* 알림 등록 */
	void register(NotificationResponse notificationResponse) throws Exception;
	
	/* 알림 조회 */
	List<Notification> list(String userIdTo) throws Exception;
	
	/* 알림 삭제 */
	void delete(String notificationIdx) throws Exception;
}
