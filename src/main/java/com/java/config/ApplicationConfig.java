package com.java.config;

/**
 * class to have elements of registry and load balancer
 */
public class ApplicationConfig {

    private RegistryConfig registry;

    private LoadBalancerConfig loadBalancer;

    public RegistryConfig getRegistry() {
        return registry;
    }

    public void setRegistry(RegistryConfig registry) {
        this.registry = registry;
    }

    public LoadBalancerConfig getLoadBalancer() {
        return loadBalancer;
    }

    public void setLoadBalancer(LoadBalancerConfig loadBalancer) {
        this.loadBalancer = loadBalancer;
    }
}
