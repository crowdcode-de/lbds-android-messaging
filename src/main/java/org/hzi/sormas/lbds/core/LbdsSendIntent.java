package org.hzi.sormas.lbds.core;

import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpMethod;

import static org.hzi.sormas.lbds.core.http.HttpContainer.deserializePackedHttpContainer;
import static org.hzi.sormas.lbds.core.http.HttpContainer.serializePackedHttpContainer;

public class LbdsSendIntent extends LbdsBaseIntent {

    public LbdsSendIntent(HttpMethod method) {
        super(new HttpContainer(method));
    }


    public LbdsSendIntent(HttpContainer container) {
        super(container);
    }

    public HttpMethod getHttpMethod(){
        final HttpContainer container = getHttpContainer();
        return container.getMethod();
    }

}
