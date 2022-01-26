package com.java.config;

import com.java.core.BalancingStrategy;

/**
 * define load balancer configurations mentioned in application-config.yaml file in a class
 */
public class LoadBalancerConfig {

    private BalancingStrategy defaultStrategy;

    private int maxConcurrentWorkersPerProvider;

    public BalancingStrategy getDefaultStrategy() {
        return defaultStrategy;
    }

    public void setDefaultStrategy(BalancingStrategy defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
    }

    public int getMaxConcurrentWorkersPerProvider() {
        return maxConcurrentWorkersPerProvider;
    }

    public void setMaxConcurrentWorkersPerProvider(int maxConcurrentWorkersPerProvider) {
        this.maxConcurrentWorkersPerProvider = maxConcurrentWorkersPerProvider;
    }
}
