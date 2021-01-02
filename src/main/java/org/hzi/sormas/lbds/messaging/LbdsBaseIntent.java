package org.hzi.sormas.lbds.messaging;

import android.content.Intent;
import android.util.Base64;
import org.hzi.sormas.lbds.core.http.HttpContainer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

import static org.hzi.sormas.lbds.core.http.HttpContainer.deserializePackedHttpContainer;
import static org.hzi.sormas.lbds.core.http.HttpContainer.serializePackedHttpContainer;
import static org.hzi.sormas.lbds.messaging.Constants.HTTP_CONTAINER;
import static org.hzi.sormas.lbds.messaging.Constants.INTENT_TYPE;

public class LbdsBaseIntent extends android.content.Intent implements IntentTypeCarrying {

    LbdsBaseIntent(Intent intent){
        super(ACTION_SEND);
        putExtras(intent);
    }

    protected LbdsBaseIntent(HttpContainer container, String secret) {
        super(ACTION_SEND);
        fillIntent(container, secret);
        putExtra(INTENT_TYPE, IntentTypeCarrying.toIntentType(this).toString());
    }


    protected void fillIntent(HttpContainer container, String secret) {
        final String httpContainer = serializePackedHttpContainer(container);
        putExtra(HTTP_CONTAINER, encryptContainer(httpContainer, secret));
        setType("application/text");
    }


    public HttpContainer getHttpContainer(String secret) {
        final String encryptedContainer = getStringExtra(HTTP_CONTAINER);
        final HttpContainer container;

        if (encryptedContainer != null && !encryptedContainer.trim().isEmpty()) {
            final String httpContainer = decryptContainer(secret, encryptedContainer);
            container = deserializePackedHttpContainer(httpContainer);
        } else {
            container = null;
        }
        return container;
    }

    private String decryptContainer(String secret, String encryptedString) {
        try {
            byte[] key = secret.getBytes(StandardCharsets.UTF_8);
            final byte[] bytes = Base64.decode(encryptedString.getBytes(StandardCharsets.UTF_8),0);
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(bytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String encryptContainer(String httpContainer, String secret) {
        try {
            byte[] key = secret.getBytes(StandardCharsets.UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeToString(cipher.doFinal(httpContainer.getBytes(StandardCharsets.UTF_8)),0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
