package org.hzi.sormas.lbds.core;

import android.content.ComponentName;
import android.content.Context;
import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpMethod;

import static org.hzi.sormas.lbds.core.http.HttpContainer.deserializePackedHttpContainer;
import static org.hzi.sormas.lbds.core.http.HttpContainer.serializePackedHttpContainer;

public class LbdsSendIntent extends LbdsBaseIntent {

    public static final String LBDS_SERVICE_APP_PCKG="org.hzi.sormas.lbds.lbds_service_app";
    public static final String LBDS_RECEIVER_COMPONENT="org.hzi.sormas.lbds.lbds_service_app.comm.LbdsIntentService";

    final ComponentName componentName = new ComponentName(LBDS_SERVICE_APP_PCKG, LBDS_RECEIVER_COMPONENT);

    public LbdsSendIntent(HttpMethod method) {
        super(new HttpContainer(method));
        setComponent(componentName);

    }

    public LbdsSendIntent(HttpContainer container) {
        super(container);
        setComponent(componentName);
    }

//    public LbdsSendIntent(Context packageContext, java.lang.Class<?> cls, HttpMethod method) {
//        super(packageContext, cls, new HttpContainer(method));
//        setComponent(componentName);
//    }

    public HttpMethod getHttpMethod(){
        final HttpContainer container = getHttpContainer();
        return container.getMethod();
    }

}
