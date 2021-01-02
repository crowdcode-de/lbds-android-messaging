package org.hzi.sormas.lbds.messaging;

import android.content.Intent;

import java.security.PublicKey;

public class LbdsPropagateKexToSormasIntent extends LbdsKexIntent implements SormasRelated {

    LbdsPropagateKexToSormasIntent(Intent intent){
        super(intent);
    }

    public LbdsPropagateKexToSormasIntent(PublicKey lbdsKey) {
        super();
        setComponent(componentName);
        setLbdsKey(lbdsKey);
    }
}
