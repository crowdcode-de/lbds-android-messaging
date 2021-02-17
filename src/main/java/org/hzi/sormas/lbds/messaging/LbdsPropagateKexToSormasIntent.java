package org.hzi.sormas.lbds.messaging;

import android.content.Intent;

import java.security.PublicKey;

public class LbdsPropagateKexToSormasIntent extends LbdsKexIntent implements SormasRelated {

    LbdsPropagateKexToSormasIntent(Intent intent){
        super(intent);
    }

    /**
     *
     * @param lbdsKey - lbds public key
     * @param sormasPublicKey - sormas public to encrypt the aes key
     * @param aesKey - base64 encoded aes key
     */
    public LbdsPropagateKexToSormasIntent(PublicKey lbdsKey, PublicKey sormasPublicKey, String aesKey) {
        super();
        setComponent(componentName);
        setLbdsKey(lbdsKey);
        setAesSecret(aesKey, sormasPublicKey);
    }
}
