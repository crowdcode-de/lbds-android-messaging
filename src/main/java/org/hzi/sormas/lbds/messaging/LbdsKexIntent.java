package org.hzi.sormas.lbds.messaging;

import org.hzi.sormas.lbds.messaging.util.KeySerializationUtil;

import java.security.PublicKey;

import static org.hzi.sormas.lbds.messaging.Constants.*;

public class LbdsKexIntent extends android.content.Intent implements IntentTypeCarrying {

    protected LbdsKexIntent() {
        super(ACTION_SEND);
        putExtra(INTENT_TYPE, toIntentType(this).toString());
    }

    public PublicKey getLbdsKey() {
        final java.lang.String lbds_key = getStringExtra(LBDS_KEY);
        if (lbds_key != null) {
            return KeySerializationUtil.deserializePublicKey(lbds_key);
        } else {
            return null;
        }


    }

    public void setLbdsKey(PublicKey lbdsKey) {
        putExtra(LBDS_KEY, KeySerializationUtil.serializePublicKey(lbdsKey));
    }

    public PublicKey getSormasKey() {
        final java.lang.String lbds_key = getStringExtra(SORMAS_KEY);
        if (lbds_key != null) {
            return KeySerializationUtil.deserializePublicKey(lbds_key);
        } else {
            return null;
        }
    }

    public void setSormasKey(PublicKey sormasKey) {
        putExtra(SORMAS_KEY, KeySerializationUtil.serializePublicKey(sormasKey));
    }
}
