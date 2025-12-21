package com.ajudaqui.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record ApplicationDTO(String name, String url) {
}
