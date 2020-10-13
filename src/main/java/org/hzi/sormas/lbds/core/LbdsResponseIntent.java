package org.hzi.sormas.lbds.core;

import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpResult;
import java.util.UUID;
public class LbdsResponseIntent extends LbdsBaseIntent {

    public static final String SORMAS_APP_PCK="de.symeda.sormas.app";

    public LbdsResponseIntent(HttpResult result) {
        super(new HttpContainer(UUID.randomUUID(), result), SORMAS_APP_PCK);
    }

    public LbdsResponseIntent(HttpContainer container) {
        super(container, SORMAS_APP_PCK);
    }

    public HttpResult getHttpResult() {
        final HttpContainer container = getHttpContainer();
        return container.getResult();
    }
}
