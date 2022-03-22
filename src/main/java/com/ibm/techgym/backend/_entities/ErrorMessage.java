package com.ibm.techgym.backend._entities;

/**
 * Data transfer object
 */
public class ErrorMessage {

    private String code;
    private String details;

    public ErrorMessage(String code, String details) {
        this.code = code;
        this.details = details;
    }

    public ErrorMessage(int code, String details) {
        this.code = String.valueOf(code);
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ErrorMessage [code=" + code + ", details=" + details + "]";
    }
}
