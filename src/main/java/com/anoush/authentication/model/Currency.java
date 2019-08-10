package com.anoush.authentication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Currency {
  private String code;
  private String name;
  private String symbol;

  public Currency(String code, String name, String symbol) {
    this.code = code;
    this.name = name;
    this.symbol = symbol;
  }
}
