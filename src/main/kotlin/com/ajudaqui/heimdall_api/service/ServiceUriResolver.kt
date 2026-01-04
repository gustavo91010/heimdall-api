package com.ajudaqui.heimdall_api.service

import java.util.concurrent.ConcurrentHashMap
import org.springframework.stereotype.Component

@Component
class ServiceUriResolver {

  private val routes = ConcurrentHashMap<String, String>()

  init {
    routes["py"] = "http://3.229.225.73:8183"
  }

  fun resolve(prefix: String): String? {
    return routes[prefix]
  }

  fun update(prefix: String, uri: String) {
    routes[prefix] = uri
  }

  // private val routes["py"] = "http://3.229.225.73:8183"
}
