package com.animapolis.healthcare.service.employee;

import com.animapolis.healthcare.constant.CircuitBreakerNames;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultEmployeeService implements EmployeeService {

    @Qualifier("employeeRestClient")
    private final RestClient restClient;

    @CircuitBreaker(name = CircuitBreakerNames.EMPLOYEE, fallbackMethod = "employeeFallback")
    @Override
    public boolean exists(UUID employeeResourceId) {
        return restClient.get()
                .uri("/employee/{id}", employeeResourceId)
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND, (req, res) -> {})
                .toBodilessEntity()
                .getStatusCode() == HttpStatus.OK;
    }

    public boolean employeeFallback(UUID id, Throwable t) throws Throwable {
        log.error("Employee service call failed [{}]: {}", t.getClass().getSimpleName(), t.getMessage());
        throw t;
    }
}
