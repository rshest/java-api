package io.split.api.client;

import io.split.api.SplitApiClientConfig;
import io.split.api.client.interceptors.AddSplitHeadersFilter;
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

    private final TrafficTypeClient trafficTypeClient;
    private final EnvironmentClient environmentClient;
    private final AttributeClient attributeClient;
    private final IdentityClient identityClient;

    public SplitApiClient(String apiToken, SplitApiClientConfig config) {
        _config = config;

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(config.connectionTimeout())
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(20);
        cm.setDefaultMaxPerRoute(20);
        _httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .addInterceptorLast(AddSplitHeadersFilter.instance(apiToken))
                .build();

        _rootTarget = URI.create(config.endpoint());

        trafficTypeClient = new TrafficTypeClient();
        environmentClient = new EnvironmentClient();
        attributeClient = new AttributeClient();
        identityClient = new IdentityClient(_httpClient, _rootTarget);
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
