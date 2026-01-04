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

  // override fun getOrder(): Int = Ordered.HIGHEST_PRECEDENCE
  override fun getOrder(): Int = Ordered.LOWEST_PRECEDENCE

  override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {

    // --- 1. Lógica de Roteamento (Cálculo da URI) ---
    val path = exchange.request.uri.path
    val prefix = path.split("/").getOrNull(1) ?: return chain.filter(exchange)
    val targetUri = resolver.resolve(prefix) ?: return chain.filter(exchange)
    val newPath = path.removePrefix("/$prefix")
    val newUri = URI.create(targetUri + newPath) // Calculamos o URI final

    // --- 2. Variável do Header ---
    val authHeader = exchange.request.headers.getFirst("Authorization")

    // --- 3. PRINTS DE DEBUG ---
    println("--- DynamicRouteFilter DEBUG ---")
    println("1. Original Path: $path")
    println("2. Service Prefix: $prefix")
    println("3. Target URI Base (Resolved): $targetUri")
    println("4. Final URI (To Service): $newUri")
    println("5. Authorization Header Received: ${authHeader ?: "MISSING"}")
    println("--------------------------------")
    // -----------------------------

    var currentExchange = exchange

    // --- 4. Propagação do Header (Mutação) ---
    // Se o header de Auth estiver presente, nós criamos um novo Exchange com o header na requisição
    // mutada.
    if (authHeader != null) {

      // CONSTRÓI UMA NOVA REQUISIÇÃO (MUTÁVEL) COM O HEADER
      val mutatedRequest = exchange.request.mutate().header("Authorization", authHeader).build()

      // CRIA UM NOVO EXCHANGE COM A REQUISIÇÃO MUTADA
      currentExchange = exchange.mutate().request(mutatedRequest).build()
    }

    // --- 5. Aplicação do URI de Roteamento ---
    // Aplica o URI de destino nos atributos (isso é lido pelos filtros nativos de proxy)
    currentExchange.attributes[GATEWAY_REQUEST_URL_ATTR] = newUri
    currentExchange.attributes[GATEWAY_REQUEST_URL_ATTR]?.let {
      println("GATEWAY_REQUEST_URL_ATTR = $it")
    }

    // Continua a cadeia de filtros (com o Exchange original ou o mutado)
    return chain.filter(currentExchange)
  }
}
