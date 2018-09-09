package org.kaa.moneytransfer.banking.boundary.mapper;

import org.kaa.moneytransfer.RestError;
import org.kaa.moneytransfer.banking.entity.NotEnoughMoneyException;
import org.slf4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.status;
import static org.slf4j.LoggerFactory.getLogger;

@Provider
public class NotEnoughMoneyExceptionMapper implements ExceptionMapper<NotEnoughMoneyException> {
  private static final Logger logger = getLogger(NotEnoughMoneyExceptionMapper.class);

  private final static int UNPROCESSABLE_ENTITY_CODE = 422;

  @Override
  public Response toResponse(NotEnoughMoneyException exception) {
    logger.error(exception.getMessage(), exception);
    return status(UNPROCESSABLE_ENTITY_CODE)
        .entity(new RestError(UNPROCESSABLE_ENTITY_CODE, exception.getMessage()))
        .build();
  }
}
