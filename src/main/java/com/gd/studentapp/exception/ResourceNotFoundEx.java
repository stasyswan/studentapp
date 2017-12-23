package com.gd.studentapp.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundEx extends Exception implements ExceptionMapper<ResourceNotFoundEx> {
    public ResourceNotFoundEx() {
    }

    public ResourceNotFoundEx(String message) {
        super(message);
    }

    public ResourceNotFoundEx(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Response toResponse(ResourceNotFoundEx ex) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(ex.getMessage())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
