package io.split.api.client;

import io.split.api.SplitApiClientConfig;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class SplitApiClient {
    private static final Logger _log = LoggerFactory.getLogger(SplitApiClient.class);

    private final SplitApiClientConfig _config;
    private final CloseableHttpClient _httpClient;
    private final URI _rootTarget;

    public SplitApiClient(String apiToken, SplitApiClientConfig config) {
        _config = config;

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(config.connectionTimeout())
                .build();
`
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(20);
        cm.setDefaultMaxPerRoute(20);
        _httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        _rootTarget = URI.create(config.endpoint());
    }

    public TrafficTypeClient trafficTypes() {
        return new TrafficTypeClient();
    }

    public EnvironmentClient environments() {
        return new EnvironmentClient();
    }

    public AttributeClient attributes() {
        return new AttributeClient();
    }

    public IdentityClient identities() {
        return new IdentityClient();
    }
}
