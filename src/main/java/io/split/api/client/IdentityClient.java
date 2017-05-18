package io.split.api.client;

import io.split.api.dtos.Identity;
import io.split.api.dtos.result.ResultDTO;
import io.split.api.utils.Json;
import io.split.api.utils.Utils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class IdentityClient {
    private static final Logger _log = LoggerFactory.getLogger(IdentityClient.class);

    private CloseableHttpClient client;
    private URI baseURL;

    IdentityClient(CloseableHttpClient client, URI baseURL) {
        this.client = client;
        this.baseURL = baseURL;
    }

    public Identity save(Identity identity) {
        CloseableHttpResponse response = null;

        try {
            URI uri = new URIBuilder(baseURL)
                    .setPath(String.format(
                            "/v1/trafficTypes/%s/environments/%s/identities/%s",
                            identity.trafficTypeId(),
                            identity.environmentId(),
                            identity.key()
                    ))
                    .build();
            HttpPut request = new HttpPut(uri);
            request.setEntity(Utils.toJsonEntity(identity));
            response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode < 200 || statusCode >= 300) {
                _log.error("Response status was: " + statusCode);
                throw new IllegalStateException("Could not save identity " + identity.key() + "; http return code " + statusCode);
            }

            String json = EntityUtils.toString(response.getEntity());
            if (_log.isDebugEnabled()) {
                _log.debug("Received json: " + json);
            }

            return Json.fromJson(json, Identity.class);
        } catch (Throwable t) {
            _log.error("Problem fetching segmentChanges", t);
            throw new IllegalStateException(t);
        } finally {
            Utils.forceClose(response);
        }
    }

    public ResultDTO<Identity> save(Collection<Identity> identities) {
        // Group by Traffic Type & Environment
        Map<String, List<Identity>> groups = new HashMap<>();
        for (Identity identity : identities) {
            String key = String.format("%s:%s", identity.trafficTypeId(), identity.environmentId());
            if (!groups.containsKey(key)) {
                groups.put(key, new ArrayList<Identity>());
            }
            groups.get(key).add(identity);
        }

        // Save each group
        ResultDTO<Identity> result = new ResultDTO<>();
        for (Map.Entry<String, List<Identity>> entry : groups.entrySet()) {
            if (entry.getValue().size() == 0) {
                continue;
            }
            String trafficTypeId = entry.getValue().get(0).trafficTypeId();
            String environmentId = entry.getValue().get(0).environmentId();

            ResultDTO<Identity> groupResult = save(trafficTypeId, environmentId, entry.getValue());
            result.objects().addAll(groupResult.objects());
            result.failed().addAll(groupResult.failed());
        }

        return result;
    }

    public ResultDTO<Identity> save(String trafficTypeId, String environmentId, List<Identity> identities) {
        CloseableHttpResponse response = null;

        try {
            URI uri = new URIBuilder(baseURL)
                    .setPath(String.format(
                            "/v1/trafficTypes/%s/environments/%s/identities",
                            trafficTypeId,
                            environmentId
                    ))
                    .build();
            HttpPost request = new HttpPost(uri);
            request.setEntity(Utils.toJsonEntity(identities));
            response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode < 200 || statusCode >= 300) {
                _log.error("Response status was: " + statusCode);
                throw new IllegalStateException("Could not save identities for traffic type " + trafficTypeId + " and environment " + environmentId + "; http return code " + statusCode);
            }

            String json = EntityUtils.toString(response.getEntity());
            if (_log.isDebugEnabled()) {
                _log.debug("Received json: " + json);
            }

            return Json.fromJsonResult(json, Identity.class);
        } catch (Throwable t) {
            _log.error("Problem fetching segmentChanges", t);
            throw new IllegalStateException(t);
        } finally {
            Utils.forceClose(response);
        }
    }

    public Identity update(Identity identity) throws NoSuchElementException {
        CloseableHttpResponse response = null;

        try {
            URI uri = new URIBuilder(baseURL)
                    .setPath(String.format(
                            "/v1/trafficTypes/%s/environments/%s/identities/%s/patch",
                            identity.trafficTypeId(),
                            identity.environmentId(),
                            identity.key()
                    ))
                    .build();
            HttpPost request = new HttpPost(uri);
            request.setEntity(Utils.toJsonEntity(identity));
            response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode < 200 || statusCode >= 300) {
                _log.error("Response status was: " + statusCode);
                throw new IllegalStateException("Could not update identity " + identity.key() + "; http return code " + statusCode);
            }

            String json = EntityUtils.toString(response.getEntity());
            if (_log.isDebugEnabled()) {
                _log.debug("Received json: " + json);
            }

            return Json.fromJson(json, Identity.class);
        } catch (Throwable t) {
            _log.error("Problem fetching segmentChanges", t);
            throw new IllegalStateException(t);
        } finally {
            Utils.forceClose(response);
        }
    }

    public boolean delete(Identity identity) throws NoSuchElementException {
        return delete(identity.trafficTypeId(), identity.environmentId(), identity.key());
    }

    public boolean delete(String trafficTypeId, String environmentId, String key) throws NoSuchElementException {
        CloseableHttpResponse response = null;

        try {
            URI uri = new URIBuilder(baseURL)
                    .setPath(String.format(
                            "/v1/trafficTypes/%s/environments/%s/identities/%s",
                            trafficTypeId,
                            environmentId,
                            key
                    ))
                    .build();
            HttpDelete request = new HttpDelete(uri);
            response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode < 200 || statusCode >= 300) {
                _log.error("Response status was: " + statusCode);
                throw new IllegalStateException("Could not delete identity " + key + "; http return code " + statusCode);
            }

            String json = EntityUtils.toString(response.getEntity());
            if (_log.isDebugEnabled()) {
                _log.debug("Received json: " + json);
            }

            return Json.fromJson(json, Boolean.class);
        } catch (Throwable t) {
            _log.error("Problem fetching segmentChanges", t);
            throw new IllegalStateException(t);
        } finally {
            Utils.forceClose(response);
        }
    }
}
