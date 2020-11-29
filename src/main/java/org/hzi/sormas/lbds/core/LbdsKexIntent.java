package org.hzi.sormas.lbds.core;

import org.hzi.sormas.lbds.core.util.SerializationUtil;

import java.security.PublicKey;

public class LbdsKexIntent extends android.content.Intent {

    public static final String LBDS_KEY = "LBDS_KEY";
    public static final String SORMAS_KEY = "SORMAS_KEY";

    protected LbdsKexIntent() {
        super(ACTION_SEND);
    }

    public PublicKey getLbdsKey() {
        final java.lang.String lbds_key = getStringExtra(LBDS_KEY);
        if (lbds_key != null) {
            return SerializationUtil.deserializePublicKey(lbds_key);
        } else {
            return null;
        }
    }

    public void setLbdsKey(PublicKey lbdsKey) {
        putExtra(LBDS_KEY, SerializationUtil.serializePublicKey(lbdsKey));
    }

    public PublicKey getSormasKey() {
        final java.lang.String lbds_key = getStringExtra(SORMAS_KEY);
        if (lbds_key != null) {
            return SerializationUtil.deserializePublicKey(lbds_key);
        } else {
            return null;
        }
    }

    public void setSormasKey(PublicKey sormasKey) {
        putExtra(SORMAS_KEY, SerializationUtil.serializePublicKey(sormasKey));
    }
}
