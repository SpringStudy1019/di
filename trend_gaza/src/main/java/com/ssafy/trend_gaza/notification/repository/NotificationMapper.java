package com.ssafy.trend_gaza.notification.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.notification.dto.NotificationResponse;
import com.ssafy.trend_gaza.notification.entity.Notification;

@Mapper
public interface NotificationMapper {

	/* 알림 등록 */
	void register(NotificationResponse notificationResponse) throws SQLException;
	
	/* 알림 조회 */
	List<Notification> list(String userIdTo) throws SQLException;
	
	/* 알림 삭제 */
	void delete(Map<String, String> param) throws SQLException;
}
