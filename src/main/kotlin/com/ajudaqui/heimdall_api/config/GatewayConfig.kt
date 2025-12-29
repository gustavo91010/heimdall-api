package com.ajudaqui.heimdall_api.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfig {

  @Bean
  fun customRouteLocator(builder: RouteLocatorBuilder): RouteLocator {
    return builder.routes()
            .route("payment_route") {
              it.path("/py/**")
                      .filters { f -> f.stripPrefix(1).addRequestHeader("X-Test", "valor") }
                      .uri("http://3.229.225.73:8183")
            }
            .build()
  }
}

// curl -X GET http://localhost:8080/py/users/permission \
//   -H "Authorization: c5402f5c-2d3b-4ac0-a69c-187dd43f2dbf"
//   curl -X GET http://localhost:8080/py/payment/id/19 \
  // -H "Authorization: c5402f5c-2d3b-4ac0-a69c-187dd43f2dbf"
