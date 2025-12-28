package com.ajudaqui.service;

import static com.ajudaqui.utils.EQuerys.SELECT_BY_API_KEY;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import com.ajudaqui.dto.ApplicationDTO;
import com.ajudaqui.entity.Application;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.hibernate.orm.panache.Panache;
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

  public Application findByName(String service) {
    try {
      Query q = Panache.getEntityManager()
          .createNativeQuery(
              SELECT_BY_API_KEY.getSql(),
              Application.class);
      q.setParameter("name", service);

      return (Application) q.getSingleResult();
    } catch (NoResultException e) {
      throw new WebApplicationException(
          "API Key não cadastrada",
          Response.Status.NOT_FOUND);
    }
  }
  public Application findByApiKey(String apiKey) {
    try {
      Query q = Panache.getEntityManager()
          .createNativeQuery(
              SELECT_BY_API_KEY.getSql(),
              Application.class);
      q.setParameter("apiKey", apiKey);

      return (Application) q.getSingleResult();
    } catch (NoResultException e) {
      throw new WebApplicationException(
          "API Key não cadastrada",
          Response.Status.NOT_FOUND);
    }
  }

  @Transactional
  public void deactivate(String apiKey) {
    var app = findByApiKey(apiKey);
    if (!app.active)
      throw new WebApplicationException(
          "Aplicaçõa já esta desativada",
          Response.Status.CONFLICT);
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
