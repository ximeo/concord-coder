package com.concord.coder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserNameRequestDTO {
  @JsonProperty("id")
  private int id;
  @JsonProperty("fio_encr")
  private String encryptedFullName;
}
