package org.kaa.moneytransfer.banking.boundary.mapper;

import org.kaa.moneytransfer.RestError;
import org.kaa.moneytransfer.banking.entity.AccountDoesNotExistException;
import org.slf4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.status;
import static org.slf4j.LoggerFactory.getLogger;

@Provider
public class AccountDoesNotExistExceptionMapper implements ExceptionMapper<AccountDoesNotExistException> {
  private static final Logger logger = getLogger(AccountDoesNotExistExceptionMapper.class);

  @Override
  public Response toResponse(AccountDoesNotExistException exception) {
    logger.error(exception.getMessage(), exception);
    return status(NOT_FOUND)
        .entity(new RestError(NOT_FOUND.getStatusCode(), exception.getMessage()))
        .build();
  }
}
