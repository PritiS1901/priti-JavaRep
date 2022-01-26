package com.java.registry;

import com.java.config.RegistryConfig;
import com.java.providers.Provider;
import com.java.providers.ProviderStateListener;

import java.util.List;

public interface ProviderRegistry extends ProviderStateListener {

    void registerProvider(Provider provider) throws RegistryOperationException;

    void deregisterProvider(Provider provider);

    List<Provider> getAvailableServices();

    RegistryConfig getCurrentConfig();
}
