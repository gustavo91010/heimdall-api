package com.ajudaqui.exception;

import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

  @Override
  public Response toResponse(Throwable exception) {

    ErrorResponse error;
    System.out.println("causa: " + exception.getCause());
    System.out.println("e aqui: " + exception.getMessage());
    // UNIQUE / ORA-00001
    if (exception.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
      return Response.status(Response.Status.CONFLICT)
          .entity("Registro duplicado")
          .build();
    }

    Map<String, String> lalala = new HashMap<>();
    error = new ErrorResponse(exception.getCause().toString(), lalala);
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    // return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    // .entity("Erro interno")
    // .build();
  }

}
