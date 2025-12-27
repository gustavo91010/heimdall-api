package com.ajudaqui.dto;

import java.time.LocalDateTime;

import com.ajudaqui.entity.Application;

public class ApplicationResponseDTO {
  public Long id;
  public String name;
  public String url;
  public String apiKey;
  public boolean active;

  public LocalDateTime updatedAt;

  public ApplicationResponseDTO(Application app) {
    this.id = app.id;
    this.name = app.name;
    this.url = app.url;
    this.apiKey = app.apiKey;
    this.active = app.active;
    this.updatedAt = app.updatedAt;
  }

}
