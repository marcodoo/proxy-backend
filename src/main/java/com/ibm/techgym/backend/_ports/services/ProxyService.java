package com.ibm.techgym.backend._ports.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ibm.techgym.backend._domains.ProxyDomain;
import com.ibm.techgym.backend._entities.RandomName;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.vertx.core.eventbus.EventBus;

@ApplicationScoped
public class ProxyService {

    @ConfigProperty(name = "NAMESPACE")
    private String namespace;

    @Inject
    private EventBus event;

    @Inject
    private ProxyDomain domain;

    public RandomName check() {
        return domain.check(namespace);
    }

    public RandomName assign(String fullname) {
        RandomName randomName = domain.assign(fullname, namespace);
        event.send("assigned", randomName);
        return randomName;
    }

    public List<RandomName> assignedNames() {
        return domain.allAssigned();
    }
}