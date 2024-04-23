package com.example.membermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    // 스프링에 , 우리가 만든 어플리케이션은 아시아 서울의 시간대를 기준으로 작동한다는것을 명시
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
