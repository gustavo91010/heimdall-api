package com.ajudaqui.heimdall_api.service

import java.util.concurrent.ConcurrentHashMap
import org.springframework.stereotype.Component

@Component
class ServiceUriResolver {

  private val routes = ConcurrentHashMap<String, String>()

  init {
    routes["py"] = "http://3.229.225.73:8183/payment"
    routes["us"] = "http://3.229.225.73:8183/users"
    routes["ct"] = "http://3.229.225.73:8183/category"
    routes["ac"] = "http://3.229.225.73:8183/actuator"
  }

  fun resolve(prefix: String): String? {
    return routes[prefix]
  }

  fun update(prefix: String, uri: String) {
    routes[prefix] = uri
  }

  // ❯ curl --location 'localhost:8080/ct/id/8' \
  // --header 'Authorization: c5402f5c-2d3b-4ac0-a69c-187dd43f2dbf'
  // {"message":"Categoria não
  // localizada","timestamp":"2026-01-04T03:08:16.082717326","status":400,"error":"400
  // BAD_REQUEST"}%                              ❯ curl --location 'localhost:8080/py/id/19' \
  // --header 'Authorization: c5402f5c-2d3b-4ac0-a69c-187dd43f2dbf'
  // {"message":"Boleto não
  // encontrado.","timestamp":"2026-01-04T03:08:19.871776883","status":400,"error":"400
  // BAD_REQUEST"}%                                ❯ curl --location 'localhost:8080/us/permission'
  // \
  // --header 'Authorization: c5402f5c-2d3b-4ac0-a69c-187dd43f2dbf'
  // {"id":13,"active":true,"accessToken":"c5402f5c-2d3b-4ac0-a69c-187dd43f2dbf","isCalControl":false,"calControl":false}%
}
