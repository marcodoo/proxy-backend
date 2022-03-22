package com.ibm.techgym.backend._entities;

/**
 * Data transfer object. 
 */
public class NameInfo {

    private String address;
    private String work;
    private String short_bio;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getShort_bio() {
        return short_bio;
    }

    public void setShort_bio(String short_bio) {
        this.short_bio = short_bio;
    }

    @Override
    public String toString() {
        return "NameInfo [address=" + address + ", short_bio=" + short_bio + ", work=" + work + "]";
    }
}
