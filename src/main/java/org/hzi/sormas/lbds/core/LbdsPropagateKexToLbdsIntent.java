package org.hzi.sormas.lbds.core;

import java.security.PublicKey;

public class LbdsPropagateKexToLbdsIntent extends LbdsKexIntent implements LbdsRelated {

    public LbdsPropagateKexToLbdsIntent(PublicKey sormasKey) {
        super();
        setComponent(componentName);
        setSormasKey(sormasKey);
    }


}
