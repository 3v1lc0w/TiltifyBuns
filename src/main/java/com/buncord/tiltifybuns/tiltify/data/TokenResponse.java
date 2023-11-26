package com.buncord.tiltifybuns.tiltify.data;

public class TokenResponse {

  private String access_token;
  private String created_at;
  private Integer expires_in;
  private String refresh_token;
  private String scope;
  private String token_type;

  public String getAccess_token() {
    return access_token;
  }

  public String getCreated_at() {
    return created_at;
  }

  public Integer getExpires_in() {
    return expires_in;
  }

  public String getRefresh_token() {
    return refresh_token;
  }

  public String getScope() {
    return scope;
  }

  public String getToken_type() {
    return token_type;
  }

}
