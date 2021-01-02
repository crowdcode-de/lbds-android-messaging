package org.hzi.sormas.lbds.messaging;

import android.content.Intent;

import java.security.PublicKey;

public class LbdsPropagateKexToLbdsIntent extends LbdsKexIntent implements LbdsRelated {

    LbdsPropagateKexToLbdsIntent(Intent intent){
        super(intent);
    }

    public LbdsPropagateKexToLbdsIntent(PublicKey sormasKey) {
        super();
        setComponent(componentName);
        setSormasKey(sormasKey);
    }


}
