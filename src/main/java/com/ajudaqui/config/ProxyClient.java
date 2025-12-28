package com.ajudaqui.config;

// import java.net.http.HttpHeaders;
import jakarta.ws.rs.core.HttpHeaders;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.Url;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

@RegisterRestClient
public interface ProxyClient {

  @GET
  Response get(@Url String url, @Context HttpHeaders headers);

  @POST
  Response post(@Url String url, @Context HttpHeaders headers, String body);

  @PUT
  Response put(@Url String url, @Context HttpHeaders headers, String body);

  @PATCH
  Response path(@Url String url, @Context HttpHeaders headers, String body);

  @DELETE
  Response delete(@Url String url, @Context HttpHeaders headers);
}
