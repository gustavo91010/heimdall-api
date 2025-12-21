package com.ajudaqui.dto;

import com.ajudaqui.entity.Application;

public class ApplicationResponseDTO {
  public Long id;
  public String name;
  public String url;
  public String apiKey;

  public ApplicationResponseDTO(Application app) {
    this.id = app.id;
    this.name = app.name;
    this.url = app.url;
    this.apiKey = app.apiKey;
  }

}
