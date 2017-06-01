package io.split.api.resources;

import io.split.api.dtos.Identity;
import io.split.api.dtos.result.ResultDTO;
import io.split.api.client.HttpClient;
import io.split.api.client.utils.EncodingUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class IdentityClient {
    private HttpClient _client;

    public IdentityClient(HttpClient client) {
        this._client = client;
    }

    public Identity save(Identity identity) {
        String result = _client.put(
                identity,
                "/v1/trafficTypes/%s/environments/%s/identities/%s",
                identity.trafficTypeId(),
                identity.environmentId(),
                identity.key()
        );
        return EncodingUtil.parse(result, Identity.class);
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
        String result = _client.post(
                identities,
                "/v1/trafficTypes/%s/environments/%s/identities",
                trafficTypeId,
                environmentId
        );
        return EncodingUtil.parseResult(result, Identity.class);
    }

    public Identity update(Identity identity) throws NoSuchElementException {
        String result = _client.post(
                identity,
                "/v1/trafficTypes/%s/environments/%s/identities/%s/patch",
                identity.trafficTypeId(),
                identity.environmentId(),
                identity.key()
        );
        return EncodingUtil.parse(result, Identity.class);
    }

    public boolean delete(Identity identity) throws NoSuchElementException {
        return delete(identity.trafficTypeId(), identity.environmentId(), identity.key());
    }

    public boolean delete(String trafficTypeId, String environmentId, String key) throws NoSuchElementException {
        String result = _client.delete(
                "/v1/trafficTypes/%s/environments/%s/identities/%s",
                trafficTypeId,
                environmentId,
                key
        );
        return EncodingUtil.parse(result, Boolean.class);
    }
}
