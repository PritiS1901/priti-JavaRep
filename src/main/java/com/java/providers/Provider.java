package com.java.providers;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface Provider {

    UUID getUuid();

    String get();

    ProviderStatus check();

    Future invokeProvider(Callable func);

    boolean tearDownService();

}
