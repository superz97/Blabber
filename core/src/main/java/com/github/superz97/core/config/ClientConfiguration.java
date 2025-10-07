package com.github.superz97.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfiguration {

    @Bean
    public RestClient restClient() {
        return RestClient.create(new RestTemplate());
    }

}
