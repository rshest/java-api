package io.split.api.client;

import io.split.api.dtos.Identity;

import java.util.Collection;
import java.util.NoSuchElementException;

public class IdentityClient {

    public Identity save(Identity identity) {
        return identity;
    }

    public Collection<Identity> save(String trafficTypeId, String environmentId, Collection<Identity> identities) {
        return identities;
    }

    public Collection<Identity> save(Collection<Identity> identities) {
        // Group By trafficType & environment, then call
        // saveIdentities(trafficTypeId, environmentId, groupedIdentities);
        return identities;
    }

    public Identity update(Identity identity) throws NoSuchElementException {
        return identity;
    }

    public boolean delete(String trafficTypeId, String environmentId, String key) throws NoSuchElementException {
        return true;
    }

    public boolean delete(Identity identity) throws NoSuchElementException {
        delete(identity.trafficTypeId(), identity.environmentId(), identity.key());
        return true;
    }
}
