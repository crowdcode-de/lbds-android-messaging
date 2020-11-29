package org.hzi.sormas.lbds.core;

import android.content.ComponentName;

public interface SormasRelated {

    public static final String SORMAS_APP_PCK = "de.symeda.sormas.app";
    public static final String SORMAS_RECEIVER_COMPONENT = "de.symeda.sormas.app.LbdsRecevierComponent";
    public static final ComponentName componentName = new ComponentName(SORMAS_APP_PCK, SORMAS_RECEIVER_COMPONENT);

}
