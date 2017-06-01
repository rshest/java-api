package io.split.api.client;

import io.split.api.SplitApiClientConfig;
import io.split.api.client.interceptors.AddSplitHeadersFilter;
import io.split.api.client.utils.EncodingUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

public class HttpClient {
    private static final Logger _log = LoggerFactory.getLogger(HttpClient.class);

    private final CloseableHttpClient _client;
    private final URI _rootTarget;

    public HttpClient(String apiToken, SplitApiClientConfig config) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(config.connectionTimeout())
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(20);
        cm.setDefaultMaxPerRoute(20);
        _client = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .addInterceptorLast(AddSplitHeadersFilter.instance(apiToken))
                .build();

        _rootTarget = URI.create(config.endpoint());
    }

    public String get(String path, String... arguments) {
        HttpGet request = new HttpGet(buildURI(path, arguments));
        return executeRequest(request);
    }

    public String delete(String path, String... arguments) {
        HttpDelete request = new HttpDelete(buildURI(path, arguments));
        return executeRequest(request);
    }

    public String post(Object entity, String path, String... arguments) {
        HttpPost request = new HttpPost(buildURI(path, arguments));
        request.setEntity(toJsonEntity(entity));
        return executeRequest(request);
    }

    public String put(Object entity, String path, String... arguments) {
        HttpPut request = new HttpPut(buildURI(path, arguments));
        request.setEntity(toJsonEntity(entity));
        return executeRequest(request);
    }

    public String executeRequest(HttpRequestBase request) {
        CloseableHttpResponse response = null;

        try {
            response = _client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode < 200 || statusCode >= 300) {
                String message = String.format(
                        "Error Executing Request: method=%s path=%s status=%d",
                        request.getMethod(),
                        request.getURI().getPath(),
                        statusCode
                );
                _log.error(message);
                throw new IllegalStateException(message);
            }

            String json = EntityUtils.toString(response.getEntity());
            if (_log.isDebugEnabled()) {
                _log.debug("Received json: " + json);
            }

            return json;
        } catch (Throwable t) {
            _log.error(String.format(
                    "Error Executing Request: method=%s path=%s",
                    request.getMethod(),
                    request.getURI().getPath()
            ), t);
            throw new IllegalStateException(t);
        } finally {
            forceClose(response);
        }
    }

    private URI buildURI(String pathPattern, String... arguments) {
        String url = concatenateURL(
                _rootTarget.toString(),
                String.format(pathPattern, (Object[]) arguments)
        );
        return url != null ? URI.create(url) : null;
    }


    public static String concatenateURL(String... urls) {
        if (urls.length == 0) {
            return null;
        } else if (urls.length == 1) {
            return stripBackslash(urls[0]);
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(stripBackslash(urls[0]));
            for (int i = 1; i < urls.length; i++) {
                builder.append("/");
                builder.append(stripBackslash(urls[i], true));
            }
            return builder.toString();
        }
    }

    private static String stripBackslash(String url) {
        return stripBackslash(url, false);
    }

    private static String stripBackslash(String url, boolean stripStarting) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        if (stripStarting && url.startsWith("/")) {
            url = url.substring(1, url.length());
        }
        return url;
    }

    private static StringEntity toJsonEntity(Object obj) {
        String json = EncodingUtil.encode(obj);
        StringEntity entity = new StringEntity(json, "UTF-8");
        entity.setContentType("application/json");
        return entity;
    }

    private static void forceClose(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            // ignore
        }
    }
}
