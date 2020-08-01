package com.concord.coder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ErrorResponseDTO {
  @JsonProperty("timestamp")
  private final ZonedDateTime currentTime;
  @JsonProperty("status")
  private final int errorStatus;
  @JsonProperty("error")
  private final String errorName;
  @JsonProperty("message")
  private final String errorDescription;
  @JsonProperty("path")
  private final String controllerPath;

  public ErrorResponseDTO(int errorStatus, String errorName, String errorDescription, String path) {
    this.currentTime = ZonedDateTime.now(ZoneId.of("GMT"));
    this.errorStatus = errorStatus;
    this.errorName = errorName;
    this.errorDescription = errorDescription;
    this.controllerPath = path;
  }
}
