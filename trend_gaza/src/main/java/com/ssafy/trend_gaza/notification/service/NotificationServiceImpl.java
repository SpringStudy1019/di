package com.ssafy.trend_gaza.notification.service;


import com.ssafy.trend_gaza.notification.dto.NotificationResponse;
import com.ssafy.trend_gaza.notification.entity.Notification;
import com.ssafy.trend_gaza.notification.repository.NotificationMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationMapper notificationMapper) {
        super();
        this.notificationMapper = notificationMapper;
    }

    @Override
    public void register(NotificationResponse notificationResponse) throws Exception {
        notificationMapper.register(notificationResponse);
    }

    @Override
    public List<Notification> list(String userIdTo) throws Exception {
        return notificationMapper.list(userIdTo);
    }

    @Override
    public void delete(Map<String, String> param) throws Exception {
        //		NotificationResponse notificationResponse = new NotificationResponse();
        //		notificationResponse.setUserIdFrom(param.get("userIdFrom"));
        //		notificationResponse.setUserIdTo(param.get("userIdTo"));
        notificationMapper.delete(param);
    }
}
