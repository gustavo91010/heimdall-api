package com.ajudaqui.heimdall_api.config

import com.ajudaqui.heimdall_api.service.ServiceUriResolver
import java.net.URI
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class DynamicRouteFilter(private val resolver: ServiceUriResolver) : GlobalFilter, Ordered {

  override fun getOrder(): Int = Ordered.LOWEST_PRECEDENCE

  override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {

    val path = exchange.request.uri.path
    val prefix = path.split("/").getOrNull(1) ?: return chain.filter(exchange)
    val targetUri = resolver.resolve(prefix) ?: return chain.filter(exchange)
    val newPath = path.removePrefix("/$prefix")
    val newUri = URI.create(targetUri + newPath)

    val authHeader = exchange.request.headers.getFirst("Authorization")

    var currentExchange = exchange

    // Se o header de Auth estiver presente, nós criamos um novo Exchange com o header na requisição
    // mutada.
    if (authHeader != null) {

      // CONSTRÓI UMA NOVA REQUISIÇÃO (MUTÁVEL) COM O HEADER
      val mutatedRequest = exchange.request.mutate().header("Authorization", authHeader).build()

      // CRIA UM NOVO EXCHANGE COM A REQUISIÇÃO MUTADA
      currentExchange = exchange.mutate().request(mutatedRequest).build()
    }

    currentExchange.attributes[GATEWAY_REQUEST_URL_ATTR] = newUri

    return chain.filter(currentExchange)
  }
}
