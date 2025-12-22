package com.ajudaqui.service;

import java.util.List;
import java.util.UUID;

import com.ajudaqui.dto.ApplicationDTO;
import com.ajudaqui.dto.ApplicationResponseDTO;
import com.ajudaqui.entity.Application;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ApplicationService {

  @Inject
  @ConfigProperty(name = "app.security")
  private String security;

  @Transactional
  public Application create(ApplicationDTO dto) {
    var app = new Application();
    app.name = dto.name();
    app.url = dto.url();
    app.apiKey = UUID.randomUUID().toString();
    app.active = true;

    app.persist();
    return app;
  }

  private Application findById(Long id) {

    Application app = Application.findById(id);
    if (app == null)
      throw new WebApplicationException("Id não cadastrada",
          Response.Status.NOT_FOUND);
    return app;

  }

  public Application findByApiKey(String apiKey) {
    return Application.<Application>find("apiKey", apiKey)
        .firstResultOptional()
        .orElseThrow(() -> new WebApplicationException(
            "API Key não cadastrada",
            Response.Status.NOT_FOUND));
  }

  @Transactional
  public void deactivate(String apiKey) {
    var app = findByApiKey(apiKey);
    app.active = false;
    app.persist();
  }

  @Transactional
  public void update(Long id, ApplicationDTO dto) {
    var app = findById(id);
    if (!dto.name().isBlank())
      app.name = dto.name();
    if (!dto.url().isBlank())
      app.url = dto.url();
    app.persist();
  }

  public List<Application> listAll(String security) {
    if (!isAuthorized(security))
      throw new UnauthorizedException("Não autorizado");

    return Application.<Application>findAll().list();
  }

  private boolean isAuthorized(String security) {
    return this.security.replace("\"", "").equals(security);
  }
}
