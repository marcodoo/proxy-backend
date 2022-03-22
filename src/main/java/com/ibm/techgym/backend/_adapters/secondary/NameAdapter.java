package com.ibm.techgym.backend._adapters.secondary;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ibm.techgym.backend._entities.RandomName;
import com.ibm.techgym.backend.utility.exceptions.ClientExceptionMapper;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@Path("/api")
@RegisterRestClient(configKey = "nameservice")
@RegisterProvider(ClientExceptionMapper.class)
public interface NameAdapter {

    @GET
    @Path("/name")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public RandomName assign(@QueryParam("fullname") String fullname, @QueryParam("namespace") String namespace);

    @GET
    @Path("/check/name")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public RandomName check(@QueryParam("namespace") String namespace);

    @GET
    @Path("/assigned/names")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RandomName> allAssignedNames();
}
