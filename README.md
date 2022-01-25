# priti-JavaRep
Solution to Load Balancing Problem

**Run and test application**
Install dependencies and build application --> mvn install
Run the application. It Creates a registry and a load balancer, and perform some operations then prints out some stats.

**Step 2: Registering providers**
Registering providers is not performed directly on the load balancer itself, but rather on the registry attached to it, which is in charge of controlling and maintaining a list of available providers instances. The available state here directly means that the instance is ready for request processing.

**Step 3-4: Load balancing strategies**
Strategies logic defined in selectNextAvailableProvider() function into src/main/java/com/java/core/LoadBalancerImpl.java
•RANDOM set as default strategy in the configuration (src/main/resources/application_config.yaml)
•ROUND_ROBIN

**Step 5: Manual Inclusion / Exclusion**
The two operations are directly performed via the provider registry interface
registerProvider & deregisterProvider

**Step 6 & 7:  Health Check and Simplified Circuit breaker**
Core logic of the health checker is define in the following file: src/main/java/com/registry/ProviderHealthChecker.java

**Step 8: Capacity Limit**
If full capacity comes to be reached, the provider will automatically set itself in a BUSY state, hence automatically deregistering from the providers registry.
If all the providers comes to be BUSY, no provider will be available and request won't be processed.
A single value representing the number of concurrent requests is put under configuration in src/main/resources/application_config.yaml



