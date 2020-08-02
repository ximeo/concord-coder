package com.concord.coder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserNameResponseDTO {
  @JsonProperty("fio_encr")
  @JsonInclude(Include.NON_NULL)
  private String fullNameEncrypted;
  @JsonProperty("fio")
  @JsonInclude(Include.NON_NULL)
  private String fullName;

  public String getFullNameEncrypted() {
    return fullNameEncrypted;
  }

  public String getFullName() {
    return fullName;
  }
}
