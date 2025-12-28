package com.ajudaqui.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.Map;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.jboss.logging.Logger;

@ApplicationScoped
public class GatewayService {

  private static final Logger LOG = Logger.getLogger(GatewayService.class);

  public Response forward(
      String method,
      String app,
      UriInfo uriInfo,
      HttpHeaders headers,
      String body) {
    System.out.println("lalala");
    // 1. Descoberta (DB)
    String baseUrl = findBaseUrlFromDatabase(app);
    // Lock.infof("Base URL descoberta: %s", baseUrl);

    // 2. Monta URL final
    String path = uriInfo.getRequestUri().getPath().replace("/gateway/" + app, "");
    String targetUrl = baseUrl + path;
    LOG.infof("Redirecionando para: %s [%s]", targetUrl, method);

    // 3. Forward (exemplo simplificado)
    return Response.ok()
        .entity(Map.of(
            "method", method,
            "target", targetUrl))
        .build();
  }

  private String findBaseUrlFromDatabase(String app) {
    // busca no banco (ex: apps table)
    return "http://servico-interno";
  }
}
