package com.ajudaqui.controller;

import com.ajudaqui.dto.ApplicationDTO;
import com.ajudaqui.dto.ApplicationResponseDTO;
import com.ajudaqui.dto.MessageResponse;
import com.ajudaqui.entity.Application;
import com.ajudaqui.service.ApplicationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/v1/applications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApplicationController {

  @Inject
  ApplicationService service;

  @POST
  public Response create(ApplicationDTO dto) {
    Application app = service.create(dto);
    return Response.status(Response.Status.CREATED).entity(new ApplicationResponseDTO(app)).build();
  }

  @GET
  public List<ApplicationResponseDTO> listAll(@HeaderParam("Authorization") String security) {
    return service.listAll(security).stream().map(app -> new ApplicationResponseDTO(app)).toList();
  }

  @GET
  @Path("/{apiKey}")
  public ApplicationResponseDTO getByApiKey(@PathParam("apiKey") String apiKey) {
    return new ApplicationResponseDTO(service.findByApiKey(apiKey));
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, ApplicationDTO dto) {
    service.update(id, dto);

    return Response.ok(new MessageResponse("Atualização realizada com sucesso.")).build();

  }

  @PATCH
  @Path("/deactivate/{apiKey}")
  public Response deactivate(@PathParam("apiKey") String apiKey) {
    service.deactivate(apiKey);
    return Response.ok(new MessageResponse("Aplicação desativada com sucesso.")).build();
  }
}
