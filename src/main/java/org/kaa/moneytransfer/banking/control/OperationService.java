package org.kaa.moneytransfer.banking.control;

import org.kaa.moneytransfer.banking.entity.Operation;

import java.util.ArrayList;
import java.util.List;

public class OperationService {
  private List<Operation> operations;

  public OperationService(){
    this.operations = new ArrayList<>();
  }

  public void log(Operation operation){
    operations.add(operation);
  }

  public List<Operation> getOperations(){
    return operations;
  }
}
