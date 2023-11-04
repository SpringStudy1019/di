package com.ssafy.trend_gaza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})
public class TrendGajaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrendGajaApplication.class, args);
	}

}
