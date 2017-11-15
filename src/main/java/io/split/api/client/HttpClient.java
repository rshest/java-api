package io.split.api.client;

import io.split.api.SplitApiClientConfig;
import io.split.api.client.exceptions.SplitException;
import io.split.api.client.exceptions.SplitJsonException;
import io.split.api.client.exceptions.SplitRequestException;
import io.split.api.client.exceptions.SplitResourceNotFoundException;
import io.split.api.client.exceptions.SplitServerErrorException;
import io.split.api.client.interceptors.AddSplitHeadersFilter;
import io.split.api.client.utils.EncodingUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class HttpClient {
    private final CloseableHttpClient _client;
    private final URI _rootTarget;

    public HttpClient(String apiToken, SplitApiClientConfig config) throws SplitException {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom()
                    .useTLS()
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Unable to create support for secure connection.");
        }

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TLSv1.1", "TLSv1.2"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslsf)
                .build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(config.connectionTimeout())
                .setSocketTimeout(config.readTimeout())
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(20);
        cm.setDefaultMaxPerRoute(20);

        _client = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setSSLSocketFactory(sslsf)
                .addInterceptorLast(AddSplitHeadersFilter.instance(apiToken))
                .build();

        _rootTarget = URI.create(config.endpoint());
    }

    public String get(String path, String... arguments) throws SplitRequestException {
        HttpGet request = new HttpGet(buildURI(path, arguments));
        return executeRequest(request);
    }

    public String delete(String path, String... arguments) throws SplitRequestException {
        HttpDelete request = new HttpDelete(buildURI(path, arguments));
        return executeRequest(request);
    }

    public String post(Object entity, String path, String... arguments) throws SplitException {
        HttpPost request = new HttpPost(buildURI(path, arguments));
        request.setEntity(toJsonEntity(entity));
        return executeRequest(request);
    }

    public String put(Object entity, String path, String... arguments) throws SplitException {
        HttpPut request = new HttpPut(buildURI(path, arguments));
        request.setEntity(toJsonEntity(entity));
        return executeRequest(request);
    }

    public String patch(Object entity, String path, String... arguments) throws SplitException {
        HttpPatch request = new HttpPatch(buildURI(path, arguments));
        request.setEntity(toJsonEntity(entity));
        return executeRequest(request);
    }

    public String executeRequest(HttpRequestBase request) throws SplitRequestException {
        CloseableHttpResponse response = null;

        try {
            response = _client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode < 200 || statusCode >= 300) {
                String message = String.format(
                        "Error Executing Request: message=%s method=%s path=%s status=%d",
                        EntityUtils.toString(response.getEntity()),
                        request.getMethod(),
                        request.getURI().getPath(),
                        statusCode

                );

                switch (statusCode) {
                    case 404:
                        throw new SplitResourceNotFoundException(message);
                    case 500:
                        throw new SplitServerErrorException(message);
                    default:
                        throw new SplitRequestException(message);
                }
            }

            return EntityUtils.toString(response.getEntity());
        } catch (SplitRequestException e) {
            throw e;
        } catch (Throwable t) {
            String message = String.format(
                    "Error Executing Request: method=%s path=%s",
                    request.getMethod(),
                    request.getURI().getPath()
            );
            throw new SplitRequestException(message, t);
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

    private static StringEntity toJsonEntity(Object obj) throws SplitJsonException {
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
