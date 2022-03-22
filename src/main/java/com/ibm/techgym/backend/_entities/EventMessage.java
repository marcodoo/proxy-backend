package com.ibm.techgym.backend._entities;

/**
 * Event message.
 */
public class EventMessage<T> {

    private String action;
    private T value;

    public EventMessage(String action, T value) {
        this.action = action;
        this.value = value;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EventMessage [action=" + action + ", value=" + value + "]";
    }
}
