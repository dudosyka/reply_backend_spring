package com.reply.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GatewayApplication {
    @Bean
    fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes {
            route {
                path("/api/block/**")
                uri("lb://block-service")
            }
            route {
                path("/api/test/**")
                uri("lb://test-service")
            }
            route {
                path("/api/auth/**")
                uri("lb://authorization-server")
            }
            route {
                path("/api/check/**")
                uri("lb://authorization-server")
            }
            route {
                path("/eureka/web")
                filters {
                    setPath("/")
                }
                uri("http://localhost:8081")
            }
            route {
                path("/eureka/**")
                uri("http://localhost:8081")
            }
        }
    }
}