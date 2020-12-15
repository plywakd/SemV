package com.pai.STM.model;

public enum Type {
    TASK("TASK"),
    BUG("BUG"),
    FEATURE("FEATURE");

    private final String taskType;

    Type(String taskType) {
        this.taskType=taskType;
    }
}
