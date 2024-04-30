package com.remodelAi.Business_Manger.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class FBConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
