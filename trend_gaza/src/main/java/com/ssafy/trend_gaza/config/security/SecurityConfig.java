package com.ssafy.trend_gaza.config.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.trend_gaza.config.security.exception.CustomAccessDeniedHandler;
import com.ssafy.trend_gaza.config.security.exception.CustomAuthenticationEntryPoint;
import com.ssafy.trend_gaza.config.security.filter.JwtRequestFilter;


@EnableWebSecurity
public class SecurityConfig {
	
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final JwtRequestFilter jwtRequestFilter;
    
    public SecurityConfig(CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
    		CustomAccessDeniedHandler customAccessDeniedHandler, JwtRequestFilter jwtRequestFilter) {
    	this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    	this.customAccessDeniedHandler = customAccessDeniedHandler;
    	this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/review/**").hasRole("USER")
                //.antMatchers("/images/**").hasRole("USER")
                .antMatchers("/user/**", "/images/**").permitAll()

                .and()
                .exceptionHandling()
                    .accessDeniedHandler(customAccessDeniedHandler)
                    .authenticationEntryPoint(customAuthenticationEntryPoint)

                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        // 쿠키를 전송하려면 이 값을 true로 설정해야합니다.
        configuration.setAllowCredentials(true);

        // pre-flight 요청의 캐시 기간을 설정합니다.
        configuration.setMaxAge(43200L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
