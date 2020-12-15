package com.pai.STM.model;

public enum Status {
    NEW("NEW"),
    IN_PROGRESS("IN PROGRESS"),
    DONE("DONE");

    private final String statusType;

    Status(String statusType){
        this.statusType=statusType;
    }
}
