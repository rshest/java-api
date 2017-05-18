package io.split.api;

import io.split.api.resources.AttributeClient;
import io.split.api.resources.EnvironmentClient;
import io.split.api.resources.IdentityClient;
import io.split.api.resources.TrafficTypeClient;
import io.split.client.HttpClient;

public class SplitApiClient {
    private final HttpClient _httpClient;

    private final TrafficTypeClient trafficTypeClient;
    private final EnvironmentClient environmentClient;
    private final AttributeClient attributeClient;
    private final IdentityClient identityClient;

    public static SplitApiClient client(String apiToken) {
        return client(apiToken, SplitApiClientConfig.builder().build());
    }

    public static synchronized SplitApiClient client(String apiToken, SplitApiClientConfig config) {
        return new SplitApiClient(apiToken, config);
    }

    private SplitApiClient(String apiToken, SplitApiClientConfig config) {
        this._httpClient = new HttpClient(apiToken, config);

        trafficTypeClient = new TrafficTypeClient(_httpClient);
        environmentClient = new EnvironmentClient(_httpClient);
        attributeClient = new AttributeClient(_httpClient);
        identityClient = new IdentityClient(_httpClient);
    }

    public TrafficTypeClient trafficTypes() {
        return trafficTypeClient;
    }

    public EnvironmentClient environments() {
        return environmentClient;
    }

    public AttributeClient attributes() {
        return attributeClient;
    }

    public IdentityClient identities() {
        return identityClient;
    }
}
