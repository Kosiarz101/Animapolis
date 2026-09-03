package org.animapolis.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorrelationIdFilter implements GlobalFilter {

    private final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String correlationId = exchange.getRequest()
                .getHeaders()
                .getFirst(CORRELATION_ID_HEADER);

        if (!StringUtils.hasText(correlationId)) {
            correlationId = UUID.randomUUID().toString();

            String correlationIdCopy = correlationId;
            exchange = exchange.mutate()
                    .request(request -> request.headers(headers ->
                            headers.set(CORRELATION_ID_HEADER, correlationIdCopy)))
                    .build();
        }

        exchange.getResponse()
                .getHeaders()
                .set(CORRELATION_ID_HEADER, correlationId);

        return chain.filter(exchange);
    }
}
