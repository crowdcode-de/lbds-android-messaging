package org.hzi.sormas.lbds.messaging;

import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpResult;

import java.util.UUID;

public class LbdsResponseIntent extends LbdsBaseIntent implements SormasRelated {

    public LbdsResponseIntent(HttpResult result) {
        super(new HttpContainer(UUID.randomUUID(), result));
        setComponent(componentName);
    }

    public LbdsResponseIntent(HttpContainer container) {
        super(container);
        setComponent(componentName);
    }

    public HttpResult getHttpResult() {
        final HttpContainer container = getHttpContainer();
        return container.getResult();
    }
}
