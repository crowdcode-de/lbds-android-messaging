package org.hzi.sormas.lbds.core;

import org.hzi.sormas.lbds.core.http.HttpContainer;

import static org.hzi.sormas.lbds.core.http.HttpContainer.deserializePackedHttpContainer;
import static org.hzi.sormas.lbds.core.http.HttpContainer.serializePackedHttpContainer;

public class LbdsBaseIntent extends android.content.Intent {


//    public LbdsBaseIntent(Context packageContext, java.lang.Class<?> cls, HttpContainer container) {
//        super(packageContext, cls);
//        setAction(ACTION_SEND);
//        fillIntent(container);
//    }

    protected  LbdsBaseIntent(HttpContainer container) {
        super(ACTION_SEND);
        fillIntent(container);
    }


    protected void fillIntent(HttpContainer container) {
        final String httpContainer = serializePackedHttpContainer(container);
        putExtra("httpContainer", httpContainer);
        setType("application/json");
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
