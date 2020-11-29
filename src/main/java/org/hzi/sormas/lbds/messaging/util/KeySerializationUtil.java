package org.hzi.sormas.lbds.messaging.util;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

public class KeySerializationUtil {

    public static String serializePublicKey(PublicKey key) {
        final RSAPublicKey publicKey = (RSAPublicKey) key;
        final String result = publicKey.getModulus().toString() + "|" +
                publicKey.getPublicExponent().toString();
        return result;
    }

    public static PublicKey deserializePublicKey(String input) {
        try {
            final String[] parts = input.split("\\|");
            final RSAPublicKeySpec Spec = new RSAPublicKeySpec(
                    new BigInteger(parts[0]),
                    new BigInteger(parts[1]));
            final PublicKey key = KeyFactory.getInstance("RSA").generatePublic(Spec);
            return key;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
