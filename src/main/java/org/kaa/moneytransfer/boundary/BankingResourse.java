package org.kaa.moneytransfer.boundary;

import com.google.inject.Inject;
import org.kaa.moneytransfer.control.BankingService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;

@Path("/banking")
@Produces(APPLICATION_JSON)
public class BankingResourse {

  private BankingService bankingService;

  @Inject
  public BankingResourse(BankingService bankingService){
    this.bankingService = bankingService;
  }

  @GET
  @Path("/ping")
  public String ping(){
    return "pong";
  }

  @GET
  @Path("/account/transfer/{from}/{to}")
  public Response transfer(@PathParam("from") long from, @PathParam("to") long to, @QueryParam("amount") long amount){
    bankingService.transfer(from, to, amount);
    return ok(String.format("Amount %d was transfered from %d account to %d account", amount, from, to)).build();
  }

  @GET
  @Path("/account/{id}")
  public Response account(@PathParam("id") long id){
    return ok(bankingService.getAccount(id)).build();
  }

  @GET
  @Path("/operations")
  public Response operations(){
    return ok(bankingService.operations()).build();
  }
}
