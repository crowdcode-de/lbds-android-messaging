package org.hzi.sormas.lbds.messaging;

import android.content.Intent;
import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpMethod;

public class LbdsSendIntent extends LbdsBaseIntent implements LbdsRelated {

    public LbdsSendIntent(HttpMethod method, String secret) {
        super(new HttpContainer(method), secret);
        setComponent(componentName);

    }

    public LbdsSendIntent(HttpContainer container, String secret) {
        super(container, secret);
        setComponent(componentName);
    }

    LbdsSendIntent(Intent intent){
        super(intent);
    }

//    public LbdsSendIntent(Context packageContext, java.lang.Class<?> cls, HttpMethod method) {
//        super(packageContext, cls, new HttpContainer(method));
//        setComponent(componentName);
//    }

    public HttpMethod getHttpMethod(String secret){
        final HttpContainer container = getHttpContainer(secret);
        return container.getMethod();
    }

}
