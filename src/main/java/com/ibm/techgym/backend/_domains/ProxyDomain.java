package com.ibm.techgym.backend._domains;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ibm.techgym.backend._entities.RandomName;
import com.ibm.techgym.backend._ports.repositories.NameRepository;

@ApplicationScoped
public class ProxyDomain {

    @Inject
    private NameRepository nameRepository;

    /**
     * Assign a random name to a specific namespace.
     * 
     * @param fullname  Full name.
     * @param namespace Unique identifier.
     * @return Assigned {@link RandomName}.
     */
    public RandomName assign(String fullname, String namespace) {
        return nameRepository.assign(fullname, namespace);
    }

    /**
     * Check if a random name is assigned to a namespace.
     * 
     * @param namespace Unique identifier.
     * @return Assigned {@link RandomName}.
     */
    public RandomName check(String namespace) {
        return nameRepository.check(namespace);
    }

    /**
     * Return all names assigned to a namespace.
     */
    public List<RandomName> allAssigned() {
        return nameRepository.allAssignedNames();
    }
}
