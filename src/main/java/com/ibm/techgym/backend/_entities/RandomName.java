package com.ibm.techgym.backend._entities;

/**
 * Data transfer object. 
 */
public class RandomName {

    private Long id;
    private String name;
    private String fullname;
    private String namespace;
    private NameInfo details;
    private String ticket;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    public NameInfo getDetails() {
        return details;
    }

    public void setDetails(final NameInfo details) {
        this.details = details;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(final String ticket) {
        this.ticket = ticket;
    }

    

    @Override
    public String toString() {
        return "RandomName [details=" + details + ", fullname=" + fullname + ", id=" + id + ", name=" + name
                + ", namespace=" + namespace + ", ticket=" + ticket + "]";
    }
}
