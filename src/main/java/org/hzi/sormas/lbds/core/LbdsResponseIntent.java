package org.hzi.sormas.lbds.core;

import android.content.ComponentName;
import android.content.Context;
import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpResult;
import java.util.UUID;
public class LbdsResponseIntent extends LbdsBaseIntent {

    public static final String SORMAS_APP_PCK="de.symeda.sormas.app";
    public static final String SORMAS_RECEIVER_COMPONENT="de.symeda.sormas.app.LbdsRecevierComponent";
    public static final ComponentName componentName = new ComponentName(SORMAS_APP_PCK, SORMAS_RECEIVER_COMPONENT);


//    public LbdsResponseIntent(Context packageContext, java.lang.Class<?> cls, HttpContainer container) {
//        super(packageContext, cls, container);
//        setComponent(componentName);
//    }
//
//    public LbdsResponseIntent(Context packageContext, java.lang.Class<?> cls, HttpResult result) {
//        super(packageContext, cls, new HttpContainer(UUID.randomUUID(), result));
//        setComponent(componentName);
//    }

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
