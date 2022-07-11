package org.hzi.sormas.lbds.messaging;

import android.content.Intent;
import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpResult;

import java.util.UUID;

public class LbdsResponseIntent extends LbdsBaseIntent implements SormasRelated {

    LbdsResponseIntent(Intent intent){
        super(intent);
    }

    public LbdsResponseIntent(HttpResult result, String secret) {
        super(new HttpContainer(UUID.randomUUID(), result), secret);
        setComponent(componentName);
    }

    public LbdsResponseIntent(HttpContainer container, String secret) {
        super(container, secret);
        setComponent(componentName);
    }

    public HttpResult getHttpResult(String secret) {
        final HttpContainer container = getHttpContainer(secret);
        return container.getResult();
    }

    protected LbdsResponseIntent(String encryptedBody) {
        super(encryptedBody);
    }
}
