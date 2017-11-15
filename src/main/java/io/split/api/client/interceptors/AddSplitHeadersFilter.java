package io.split.api.client.interceptors;

import io.split.api.client.exceptions.SplitRequestException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class AddSplitHeadersFilter implements HttpRequestInterceptor {

    private final String _apiTokenBearer;

    public static AddSplitHeadersFilter instance(String apiToken) throws SplitRequestException {
        return new AddSplitHeadersFilter(apiToken);
    }

    private AddSplitHeadersFilter(String apiToken) {
        _apiTokenBearer = "Bearer " + apiToken;
    }

    @Override
    public void process(HttpRequest request, HttpContext httpContext) throws HttpException, IOException {
        request.addHeader("Authorization", _apiTokenBearer);
    }
}
