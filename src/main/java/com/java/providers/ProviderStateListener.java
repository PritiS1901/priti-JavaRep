package com.java.providers;

public interface ProviderStateListener {
    void providerStateChanged(Provider provider, ProviderStatus newStatus);
}
