package com.mycompany.myapp.config;

public enum LinkType {
    LINK1("l1"),
    LINK11("l11"),
    LINK16("l16"),
    UNKNOWN("");

    private String value;

    LinkType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
