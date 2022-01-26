package com.java.core;
/*
 * for balancing strategies as we can have only 2 strategies as of now
 */
public enum BalancingStrategy {
    RANDOM("RANDOM"),
    ROUND_ROBIN("ROUND_ROBIN");

    private String value;

    private BalancingStrategy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
