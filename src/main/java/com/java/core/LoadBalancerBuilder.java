package com.java.core;

import com.java.config.ApplicationConfig;
import com.java.config.ConfigProvider;
import com.java.registry.InMemoryProviderRegistry;
import com.java.registry.ProviderRegistry;

public class LoadBalancerBuilder {

    private ProviderRegistry registry;

    private BalancingStrategy strategy;

    public LoadBalancerBuilder() {
        ApplicationConfig config = ConfigProvider.getConfig();
        this.strategy = config.getLoadBalancer().getDefaultStrategy();
        this.registry = new InMemoryProviderRegistry(config.getRegistry());
    }

    public LoadBalancerBuilder withBalancingStrategy(BalancingStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public LoadBalancerBuilder withRegistry(ProviderRegistry registry) {
        this.registry = registry;
        return this;
    }

    public LoadBalancer build() {
        return new LoadBalancerImpl(this.registry, this.strategy);
    }
}
