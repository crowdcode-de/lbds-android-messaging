package org.hzi.sormas.lbds.core;

import android.content.ComponentName;

public interface LbdsRelated {

    public static final String LBDS_SERVICE_APP_PCKG = "org.hzi.sormas.lbds.lbds_service_app";
    public static final String LBDS_RECEIVER_COMPONENT = "org.hzi.sormas.lbds.lbds_service_app.comm.LbdsIntentService";

    public final ComponentName componentName = new ComponentName(LBDS_SERVICE_APP_PCKG, LBDS_RECEIVER_COMPONENT);

}
