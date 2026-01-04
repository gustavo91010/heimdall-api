package com.ajudaqui.heimdall_api.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfig {

  @Bean
  fun customRouteLocator(builder: RouteLocatorBuilder): RouteLocator =
          builder.routes()
                  .route("dynamic_route") { it.path("/{service}/**").uri("http://placeholder") }
                  .build()
}
