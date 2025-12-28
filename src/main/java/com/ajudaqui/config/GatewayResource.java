package com.ajudaqui.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import com.ajudaqui.entity.Application;
import com.ajudaqui.service.ApplicationService;

import org.jboss.logging.Logger;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.logging.Log;

@Path("/gateway")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class GatewayResource {

  private static final Logger LOG = Logger.getLogger(GatewayResource.class);

  @Inject
  GatewayService gatewayService;

  @GET
  @Path("/{app}/**")
  public Response proxyGet(
      @PathParam("app") String app,
      @Context UriInfo uriInfo,
      @Context HttpHeaders headers) {

    Log.infof("GET -> app=%s uri=%s", app, uriInfo.getRequestUri());
    return gatewayService.forward("GET", app, uriInfo, headers, null);
  }

  @POST
  @Path("/{app}/**")
  public Response proxyPost(
      @PathParam("app") String app,
      @Context UriInfo uriInfo,
      @Context HttpHeaders headers,
      String body) {

    LOG.infof("POST -> app=%s uri=%s", app, uriInfo.getRequestUri());
    return gatewayService.forward("POST", app, uriInfo, headers, body);
  }
}
// @Path("/{servcie}/{path:.*}")
// @Produces(MediaType.APPLICATION_JSON)
// public class GatewayResource {

// // ServicesConfig services;
// @Inject
// ApplicationService services;

// @Inject
// @RestClient
// ProxyClient proxy;

// @GET
// @Path("/{service}/{path:.*}")
// public Response get(
// @PathParam("service") String service,
// @PathParam("path") String path,
// @Context HttpHeaders headers) {
// return handle("GET", service, path, headers, null);
// }

// @POST
// @Path("/{service}/{path:.*}")
// public Response post(
// @PathParam("service") String service,
// @PathParam("path") String path,
// @Context HttpHeaders headers,
// String body) {
// return handle("POST", service, path, headers, body);
// }

// @PUT
// @Path("/{service}/{path:.*}")
// public Response put(
// @PathParam("service") String service,
// @PathParam("path") String path,
// @Context HttpHeaders headers,
// String body) {
// return handle("PUT", service, path, headers, body);
// }

// @DELETE
// @Path("/{service}/{path:.*}")
// public Response delete(
// @PathParam("service") String service,
// @PathParam("path") String path,
// @Context HttpHeaders headers) {
// return handle("DELETE", service, path, headers, null);
// }

// private Response handle(
// String method,
// String service,
// String path,
// HttpHeaders headers,
// String body) {
// System.out.println("services " + service);
// Application app = services.findByName(service);

// if (app == null) {
// throw new NotFoundException("Serviço não encontrado");
// }

// System.out.println("url " + app.url);
// String url = app.url + "/" + path;
// System.out.println("url final " + url);

// url = "http://localhost:8080/v1/applications";
// return switch (method) {
// case "GET" -> proxy.get(url, headers);
// case "POST" -> proxy.post(url, headers, body);
// case "PUT" -> proxy.put(url, headers, body);
// case "DELETE" -> proxy.delete(url, headers);
// default -> Response.status(405).build();
// };
// }
// // @GET
// // @POST
// // @PUT
// // @DELETE
// // @Path("")
// // public Response proxy(
// // @PathParam("service") String service,
// // @PathParam("path") String path,
// // @Context UriInfo uriInfo,
// // @Context ContainerRequestContext ctx,
// // @Context HttpHeaders headers,
// // String body) {

// // String baseUrl = resolveService(service);
// // System.out.println("baseUrl " + baseUrl);
// // String url = baseUrl + "/" + path;
// // System.out.println("full url " + url);
// // String method = uriInfo.getRequestUri().getPath();

// // return switch (ctx.getMethod()) {
// // case "GET" -> proxy.get(url, headers);
// // case "POST" -> proxy.post(url, headers, body);
// // case "PUT" -> proxy.put(url, headers, body);
// // case "DELETE" -> proxy.delete(url, headers);
// // default -> Response.status(405).build();
// // };
// // // return forward(uriInfo.getRequestUri().toString(), url, headers, body);
// // }

// // private Response forward(String method, String url, HttpHeaders headers,
// // String body) {
// // return switch (method) {
// // case "GET" -> proxy.get(url, headers);
// // case "POST" -> proxy.post(url, headers, body);
// // case "PATCH" -> proxy.put(url, headers, body);
// // case "PUT" -> proxy.put(url, headers, body);
// // case "DELETE" -> proxy.delete(url, headers);
// // default -> Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
// // };
// // }

// private String resolveService(String service) {
// System.out.println();
// System.out.println();
// System.out.println();
// System.out.println("chega aqui pedindo o service? " + service);
// System.out.println();
// System.out.println();
// System.out.println();
// // Colocar esse versico aqui em memoria comsumindo do banco
// // Quando um novo cliente for salvo, reiniicar essa informacao em memoria
// // return switch (service) {
// // case "auth" -> services.auth().baseUrl();
// // case "payment" -> services.payment().baseUrl();
// // default -> throw new NotFoundException("Serviço não encontrado");
// Application app = services.findByName(service);
// System.out.println("url app " + app.url);
// return "http://localhost:8080/v1/applications";
// };
// }
