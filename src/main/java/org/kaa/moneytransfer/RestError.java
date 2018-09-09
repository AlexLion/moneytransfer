package org.kaa.moneytransfer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestError {
  private int code;
  private String message;
}
