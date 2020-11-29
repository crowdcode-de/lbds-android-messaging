package org.hzi.sormas.lbds.messaging;

import java.security.PublicKey;

public class LbdsPropagateKexToLbdsIntent extends LbdsKexIntent implements LbdsRelated {

    public LbdsPropagateKexToLbdsIntent(PublicKey sormasKey) {
        super();
        setComponent(componentName);
        setSormasKey(sormasKey);
    }


}
