package org.hzi.sormas.lbds.core;

import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpResult;
import java.util.UUID;
public class LbdsResponseIntent extends LbdsBaseIntent {

    public LbdsResponseIntent(HttpResult result) {
        super(new HttpContainer(UUID.randomUUID(), result));
    }

    public LbdsResponseIntent(HttpContainer container) {
        super(container);
    }

    public HttpResult getHttpResult() {
        final HttpContainer container = getHttpContainer();
        return container.getResult();
    }
}
