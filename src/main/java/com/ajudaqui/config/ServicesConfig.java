package com.ajudaqui.config;

import io.smallrye.config.ConfigMapping;

// @ConfigMapping(prefix = "services")
public interface ServicesConfig {

  Service auth();

  Service applications();

  Service payment();

  interface Service {
    String baseUrl();
  }
}
