package com.fadedbytes.BinaryElementalOrbs.server;

public enum ServerStatus {

    OPEN("open"),
    MAINTENANCE("maintenance"),
    CLOSED("closed"),
    UNDEFINED("undefined");

    private final String description;
    ServerStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
