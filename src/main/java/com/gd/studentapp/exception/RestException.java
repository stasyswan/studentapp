package com.gd.studentapp.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestException extends Exception implements ExceptionMapper<RestException> {

    private static final Logger logger = LogManager.getLogger(RestException.class);

    public RestException() {
    }

    public RestException(String message) {
        super(message);
        logger.info("RestException: " + message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
        logger.info("RestException: " + message + ". " + cause.getMessage());
    }

    @Override
    public Response toResponse(RestException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
