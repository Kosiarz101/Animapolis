package com.animapolis.healthcare.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    @Primary
    public RestClient.Builder defaultRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestClient.Builder employeeRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public RestClient employeeRestClient(@Qualifier("employeeRestClientBuilder") RestClient.Builder builder) {
        return builder.baseUrl("http://Employee").build();
    }
}
