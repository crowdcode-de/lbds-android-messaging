package org.hzi.sormas.lbds.core;

import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpMethod;

import static org.hzi.sormas.lbds.core.http.HttpContainer.deserializePackedHttpContainer;
import static org.hzi.sormas.lbds.core.http.HttpContainer.serializePackedHttpContainer;
import java.util.UUID;

public class LbdsBaseIntent extends android.content.Intent {

    protected  LbdsBaseIntent(HttpContainer container) {
        super(ACTION_SEND);
        fillIntent(container);
    }

    protected  LbdsBaseIntent() {
        super(ACTION_SEND);
    }

    protected void fillIntent(HttpContainer container) {
        final String httpContainer = serializePackedHttpContainer(container);
        putExtra("httpContainer", httpContainer);
        setType("application/json");
        setClassName(this.getClass().getPackage().getName(), this.getClass().getName());
    }

    public HttpContainer getHttpContainer() {
        final String httpContainer = getStringExtra("httpContainer");
        final HttpContainer container;

        if (httpContainer != null && !httpContainer.trim().isEmpty()) {
            container = deserializePackedHttpContainer(httpContainer);
        } else {
            container = null;
        }
        return container;
    }
}
