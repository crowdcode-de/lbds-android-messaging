package org.hzi.sormas.lbds.core.util;

import com.google.gson.Gson;
import org.conscrypt.OpenSSLRSAPublicKey;

import java.security.PublicKey;

public class SerializationUtil {

    public static String serializePublicKey(PublicKey key) {
        return new Gson().toJson(key);
    }

    public static PublicKey deserializePublicKey(String json) {
        final OpenSSLRSAPublicKey openSSLRSAPublicKey = new Gson().fromJson(json, OpenSSLRSAPublicKey.class);
        return openSSLRSAPublicKey;
    }
}
