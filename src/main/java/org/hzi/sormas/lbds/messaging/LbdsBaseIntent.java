package org.hzi.sormas.lbds.messaging;

import org.hzi.sormas.lbds.core.http.HttpContainer;

import static org.hzi.sormas.lbds.core.http.HttpContainer.deserializePackedHttpContainer;
import static org.hzi.sormas.lbds.core.http.HttpContainer.serializePackedHttpContainer;
import static org.hzi.sormas.lbds.messaging.Constants.HTTP_CONTAINER;
import static org.hzi.sormas.lbds.messaging.Constants.INTENT_TYPE;

public class LbdsBaseIntent extends android.content.Intent implements IntentTypeCarrying {


    protected LbdsBaseIntent(HttpContainer container) {
        super(ACTION_SEND);
        fillIntent(container);
        putExtra(INTENT_TYPE, toIntentType(this).toString());
    }


    protected void fillIntent(HttpContainer container) {
        final String httpContainer = serializePackedHttpContainer(container);
        putExtra(HTTP_CONTAINER, httpContainer);
        setType("application/json");
    }

    public HttpContainer getHttpContainer() {
        final String httpContainer = getStringExtra(HTTP_CONTAINER);
        final HttpContainer container;

        
        if (httpContainer != null && !httpContainer.trim().isEmpty()) {
            container = deserializePackedHttpContainer(httpContainer);
        } else {
            container = null;
        }
        return container;
    }
}
