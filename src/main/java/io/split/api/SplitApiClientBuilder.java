package io.split.api;

import io.split.api.client.SplitApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplitApiClientBuilder {
    private static final Logger _log = LoggerFactory.getLogger(SplitApiClientBuilder.class);

    public static SplitApiClient client(String apiToken) {
        return client(apiToken, SplitApiClientConfig.builder().build());
    }

    public static synchronized SplitApiClient client(String apiToken, SplitApiClientConfig config) {
        return new SplitApiClient(apiToken, config);
    }
}
