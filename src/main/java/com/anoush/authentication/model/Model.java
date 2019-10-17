package com.anoush.authentication.model;

public static class Model {
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    public Model(String username, String password) {
      this.username = username;
      this.password = password;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
