package com.ibm.techgym.backend.utility.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

/**
 * ClientExceptionMapper
 */
public class ClientExceptionMapper implements ResponseExceptionMapper<WebApplicationException> {

    /**
     * Transform a client exception to {@link WebApplicationException}. 
     */
    public WebApplicationException toThrowable(Response response) {
        return new WebApplicationException(response);
    }
}
