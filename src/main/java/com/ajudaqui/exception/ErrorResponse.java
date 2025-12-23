package com.ajudaqui.exception;

import java.util.Map;

public record ErrorResponse(String message, Map<String, String> details) {

}
