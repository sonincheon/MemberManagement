package com.example.membermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    // 아시아 서울 시간 적용
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
