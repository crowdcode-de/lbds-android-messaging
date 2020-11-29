package org.hzi.sormas.lbds.messaging;

import java.security.PublicKey;

public class LbdsPropagateKexToSormasIntent extends LbdsKexIntent implements SormasRelated {

    public LbdsPropagateKexToSormasIntent(PublicKey lbdsKey) {
        super();
        setComponent(componentName);
        setLbdsKey(lbdsKey);
    }
}
