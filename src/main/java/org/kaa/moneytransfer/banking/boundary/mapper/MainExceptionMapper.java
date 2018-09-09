package org.kaa.moneytransfer.banking.boundary.mapper;

import org.kaa.moneytransfer.RestError;
import org.slf4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.status;
import static org.slf4j.LoggerFactory.getLogger;

@Provider
public class MainExceptionMapper implements ExceptionMapper<Exception> {
  private static final Logger logger = getLogger(MainExceptionMapper.class);

  @Override
  public Response toResponse(Exception exception) {
    logger.error(exception.getMessage(), exception);
    return status(INTERNAL_SERVER_ERROR)
        .entity(new RestError(INTERNAL_SERVER_ERROR.getStatusCode(), exception.getMessage()))
        .build();
  }
}
