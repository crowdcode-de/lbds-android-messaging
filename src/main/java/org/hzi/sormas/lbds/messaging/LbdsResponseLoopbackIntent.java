package org.hzi.sormas.lbds.messaging;

import android.content.Intent;
import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpResult;

import java.util.UUID;

/**
 * Loopback class, this is Lbds-only, don't use this in SORMAS!
 */
public class LbdsResponseLoopbackIntent extends LbdsBaseIntent implements LbdsRelated {

    LbdsResponseLoopbackIntent(Intent intent){
        super(intent);
    }

    public LbdsResponseLoopbackIntent(HttpResult result, String secret) {
        super(new HttpContainer(UUID.randomUUID(), result), secret);
        setComponent(componentName);
    }

    public LbdsResponseLoopbackIntent(HttpContainer container, String secret) {
        super(container, secret);
        setComponent(componentName);
    }

    public HttpResult getHttpResult(String secret) {
        final HttpContainer container = getHttpContainer(secret);
        return container.getResult();
    }
}
