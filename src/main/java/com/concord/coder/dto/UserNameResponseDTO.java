package com.concord.coder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserNameResponseDTO {
  @JsonProperty("fio_encr")
  @JsonInclude(Include.NON_NULL)
  private String fullNameEncrypted;
  @JsonProperty("fio")
  @JsonInclude(Include.NON_NULL)
  private String fullName;

  public UserNameResponseDTO(String fullNameEncryptionName, String fullName) {
    this.fullNameEncrypted = fullNameEncryptionName;
    this.fullName = fullName;
  }

  public String getFullNameEncrypted() {
    return fullNameEncrypted;
  }

  public void setFullNameEncrypted(String fullNameEncrypted) {
    this.fullNameEncrypted = fullNameEncrypted;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
}
