package com.ibm.techgym.backend._ports.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ibm.techgym.backend._adapters.secondary.NameAdapter;
import com.ibm.techgym.backend._entities.RandomName;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class NameRepository {

    @Inject
    @RestClient
    private NameAdapter nameAdapter;

    public RandomName assign(String fullname, String namespace) {
        return nameAdapter.assign(fullname, namespace);
    }

    public RandomName check(String namespace) {
        return nameAdapter.check(namespace);
    }

    public List<RandomName> allAssignedNames() {
        return nameAdapter.allAssignedNames();
    }
}
