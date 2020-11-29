package org.hzi.sormas.lbds.messaging;

import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpMethod;

public class LbdsSendIntent extends LbdsBaseIntent implements LbdsRelated {

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
