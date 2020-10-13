package org.hzi.sormas.lbds.core;

import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpMethod;

import static org.hzi.sormas.lbds.core.http.HttpContainer.deserializePackedHttpContainer;
import static org.hzi.sormas.lbds.core.http.HttpContainer.serializePackedHttpContainer;
import java.util.UUID;

public class LbdsBaseIntent extends android.content.Intent {

    protected  LbdsBaseIntent(HttpContainer container, String packageName) {
        super(ACTION_SEND);
        fillIntent(container, packageName);
    }


    protected void fillIntent(HttpContainer container, String packageName) {
        final String httpContainer = serializePackedHttpContainer(container);
        putExtra("httpContainer", httpContainer);
        setType("application/json");
        setClassName(packageName, this.getClass().getName());
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
