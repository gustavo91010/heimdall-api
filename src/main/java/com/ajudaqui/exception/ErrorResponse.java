package com.ajudaqui.exception;

import java.util.HashMap;
import java.util.Map;


@Data
public class ErrorResponse {

  private String message;
  private Map<String, String> details = new HashMap<>();

  public ErrorResponse(Throwable exception) {

}

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Map<String, String> getDetails() {
    return details;
  }

  private void setDetails(Map<String, String> details) {
    this.details = details;
  }

  public void addDetails(String key, String value) {
    this.details.put(key, value);
  }

}
