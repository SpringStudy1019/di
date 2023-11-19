package com.ssafy.trend_gaza.config.plan;

import com.ssafy.trend_gaza.plan.service.StateManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class StompInterceptor implements ChannelInterceptor {

    private final StateManagementService stateManagementService;

    public StompInterceptor(StateManagementService stateManagementService) {
    	this.stateManagementService = stateManagementService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);

        if(stompHeaderAccessor.getCommand() == StompCommand.CONNECT){
            String userId = stompHeaderAccessor.getFirstNativeHeader("userId");
            String channelId = stompHeaderAccessor.getFirstNativeHeader("channelId");
            String sessionId = (String) message.getHeaders().get("simpSessionId");

            System.out.println("");
            log.info("[CONNECTED] userId : {}", userId);
            log.info("[CONNECTED] channelId : {}", channelId);
            log.info("[CONNECTED] simpSessionId : {}", sessionId);
            stateManagementService.sendChannelInState(userId,channelId,sessionId);

        }
        if(stompHeaderAccessor.getCommand() == StompCommand.SUBSCRIBE){
            log.info("[SUBSCRIBE] Called");
        }

        return ChannelInterceptor.super.preSend(message, channel);
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);

        ChannelInterceptor.super.postSend(message, channel, sent);
    }
}
