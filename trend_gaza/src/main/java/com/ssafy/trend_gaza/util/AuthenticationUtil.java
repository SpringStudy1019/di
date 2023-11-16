package com.ssafy.trend_gaza.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ssafy.trend_gaza.config.security.CustomUserDetails;

@Component
public class AuthenticationUtil {
	
	public static String getCurrentUserSocialId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails currentUser = (CustomUserDetails) principal;
        return currentUser.getUsername();
    }
}
