package com.java.core;

import java.util.Map;
import java.util.concurrent.Future;

import com.java.registry.ProviderRegistry;

public interface LoadBalancer {

    // Route exposed to the external world
    Future get() throws ServiceUnavailableException;

    // Return providers registry
    ProviderRegistry getRegistry();

    // Apply balancing strategy
    void setStrategy(BalancingStrategy strategy);

    // Return metrics to calls per provider ID
    Map<String, Integer> getMetrics();

    // Clear load balancer metrics
    void clearMetrics();
}
