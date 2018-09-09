package org.kaa.moneytransfer.banking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Operation {
  private long from;
  private long to;
  private long amount;
}
