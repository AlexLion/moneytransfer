package org.kaa.moneytransfer.banking.boundary;

import org.kaa.moneytransfer.banking.control.BankingService;
import org.slf4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;
import static org.slf4j.LoggerFactory.getLogger;

@Path("/banking")
@Produces(APPLICATION_JSON)
public class BankingResourse {
  private static final Logger logger = getLogger(BankingResourse.class);

  private BankingService bankingService;

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
    logger.info(format("Amount - %d, from - %d account, to - %d account", amount, from, to));
    bankingService.transfer(from, to, amount);
    return ok(format("Amount %d was transfered from %d account to %d account", amount, from, to)).build();
  }

  @GET
  @Path("/account/{id}")
  public Response account(@PathParam("id") long id){
    logger.info(format("id - %d", id));
    return ok(bankingService.getAccount(id)).build();
  }

  @GET
  @Path("/operations")
  public Response operations(){
    return ok(bankingService.operations()).build();
  }
}
