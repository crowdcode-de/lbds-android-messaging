package org.hzi.sormas.lbds.core;

import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpMethod;

import static org.hzi.sormas.lbds.core.http.HttpContainer.deserializePackedHttpContainer;
import static org.hzi.sormas.lbds.core.http.HttpContainer.serializePackedHttpContainer;

public class LbdsSendIntent extends LbdsBaseIntent {

    public static final String LBDS_SERVICE_APP_PCKG="org.hzi.sormas.lbds.lbds_service_app";

    public LbdsSendIntent(HttpMethod method) {
        super(new HttpContainer(method), LBDS_SERVICE_APP_PCKG);
    }


    public LbdsSendIntent(HttpContainer container) {
        super(container, LBDS_SERVICE_APP_PCKG);
    }

    public HttpMethod getHttpMethod(){
        final HttpContainer container = getHttpContainer();
        return container.getMethod();
    }

}
