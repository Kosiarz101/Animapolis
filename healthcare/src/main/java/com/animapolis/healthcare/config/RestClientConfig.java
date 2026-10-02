package com.animapolis.healthcare.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.restclient.autoconfigure.RestClientBuilderConfigurer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000);
        return factory;
    }

    @Bean
    @Primary
    public RestClient.Builder defaultRestClientBuilder(RestClientBuilderConfigurer configurer) {
        return configurer.configure(RestClient.builder());
    }

    @Bean
    @LoadBalanced
    public RestClient.Builder employeeRestClientBuilder(RestClientBuilderConfigurer configurer) {
        return configurer.configure(RestClient.builder());
    }

    @Bean
    public RestClient employeeRestClient(@Qualifier("employeeRestClientBuilder") RestClient.Builder builder,
                                         ClientHttpRequestFactory requestFactory) {
        return builder.baseUrl("http://Employee")
                .requestFactory(requestFactory)
                .build();
    }
}
