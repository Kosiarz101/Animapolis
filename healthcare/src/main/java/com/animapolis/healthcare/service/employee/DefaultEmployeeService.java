package com.animapolis.healthcare.service.employee;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeService implements EmployeeService {
    private final Logger logger = LoggerFactory.getLogger(DefaultEmployeeService.class);

    @Qualifier("employeeRestClient")
    private final RestClient restClient;

    @Override
    public boolean exists(UUID employeeResourceId) {
        try {
            return restClient.get()
                    .uri("/Employee/" + employeeResourceId)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (request, response) ->
                            logger.warn("Employee service returned status: {}", response.getStatusCode()))
                    .toBodilessEntity()
                    .getStatusCode() == HttpStatusCode.valueOf(200);
        } catch (RestClientException ex) {
            logger.error("Error occurred while connecting to employee service: {}", ex.getMessage());
        }
        return false;
    }
}
