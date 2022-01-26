package com.java.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import com.java.providers.Action;
import com.java.providers.Provider;
import com.java.providers.ProviderActionFactory;
import com.java.registry.ProviderRegistry;

public class LoadBalancerImpl implements LoadBalancer {

    private final ProviderRegistry registry;

    private BalancingStrategy strategy;

    private final AtomicInteger currentProviderIndex = new AtomicInteger();

    // Quick and dirty way to get some metrics on the get calls count (usually would use a statsd client or similar stack)
    private Map<String, Integer> metricsCount;

    public LoadBalancerImpl(ProviderRegistry registry, BalancingStrategy strategy) {
        metricsCount = new HashMap<>();
        this.registry = registry;
        this.strategy = strategy;
    }

    @Override
    public Future get() throws ServiceUnavailableException {
        Provider selected = selectNextAvailableProvider();

        // Increment metrics counters
        String providerUuid = selected.getUuid().toString();
        if (metricsCount.get(providerUuid) == null) {
            metricsCount.put(providerUuid, 1);
        } else {
            metricsCount.put(providerUuid, metricsCount.get(providerUuid) + 1);
        }

        return selected.invokeProvider(new ProviderActionFactory(selected).getProviderAction(Action.GET));
    }

    private Provider selectNextAvailableProvider() throws ServiceUnavailableException {
        List<Provider> providers = registry.getAvailableServices();
        if (providers.size() == 0) {
            // Either no provider has been registered, or all of them are currently busy.
            throw new ServiceUnavailableException();
        }

        synchronized (providers) {
            Provider selected;
            switch (strategy) {
                case ROUND_ROBIN: {
                    if (currentProviderIndex.get() >= providers.size()) {
                        currentProviderIndex.set(0);
                    }
                    selected = providers.get(currentProviderIndex.get());
                    currentProviderIndex.incrementAndGet();
                    break;
                }
                default: {
                    // RANDOM Load balancing
                    Random rand = new Random();
                    selected = providers.get(rand.nextInt(providers.size()));
                }
            }
            return selected;
        }
    }

    @Override
    public ProviderRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public Map<String, Integer> getMetrics() {
        return this.metricsCount;
    }

    @Override
    public void clearMetrics() {
        this.metricsCount.clear();
    }

    @Override
    public void setStrategy(BalancingStrategy strategy) {
        this.strategy = strategy;
    }

    public BalancingStrategy getStrategy() {
        return strategy;
    }
}
