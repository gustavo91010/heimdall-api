package com.ajudaqui.config;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

  @Override
  public Response toResponse(Throwable exception) {
    System.out.println("causa: "+exception.getCause());
    System.out.println("e aqui: "+exception.getMessage());
    // UNIQUE / ORA-00001
    if (exception.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
      return Response.status(Response.Status.CONFLICT)
          .entity("Registro duplicado")
          .build();
    }

    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Erro interno")
        .build();
  }

}
