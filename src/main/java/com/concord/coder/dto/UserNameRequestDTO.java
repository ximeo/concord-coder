package com.concord.coder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserNameRequestDTO {
  @JsonProperty("id")
  private int id;
  @JsonProperty("fio_encr")
  private String encryptedFullName;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEncryptedFullName() {
    return encryptedFullName;
  }

  public void setEncryptedFullName(String encryptedFullName) {
    this.encryptedFullName = encryptedFullName;
  }
}
