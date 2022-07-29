package br.com.apigateway.config;

import br.com.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", route -> route.path("/api/v1/auth/**").filters(filter -> filter.filter(jwtAuthenticationFilter)).uri("http://localhost:8081/"))
                .route("courses", route -> route.path("/api/v1/courses/**").filters(filter -> filter.filter(jwtAuthenticationFilter)).uri("http://localhost:8082"))
                .build();
    }
}
