package com.ibm.techgym.backend._adapters.primary;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ibm.techgym.backend._entities.RandomName;
import com.ibm.techgym.backend._ports.services.ProxyService;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/api")
public class HttpResource {

    @Inject
    private ProxyService proxy;

    @ConfigProperty(name = "version")
    private String version;

    @GET
    @Path("check")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public RandomName check() {
        return proxy.check();
    }

    @GET
    @Path("assign")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public RandomName assign(@QueryParam("fullname") String fullname) {
        return proxy.assign(fullname);
    }

    @GET
    @Path("/assigned/names")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RandomName> assignedNames() {
        return proxy.assignedNames();
    }

    @GET
    @Path("/version")
    @Produces(MediaType.TEXT_PLAIN)
    public String version() {
        return version;
    }
}