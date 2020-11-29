package org.hzi.sormas.lbds.core;

import java.security.PublicKey;

public class LbdsPropagateKexToSormasIntent extends LbdsKexIntent implements SormasRelated {

    public LbdsPropagateKexToSormasIntent(PublicKey lbdsKey) {
        super();
        setComponent(componentName);
        setLbdsKey(lbdsKey);
    }
}
