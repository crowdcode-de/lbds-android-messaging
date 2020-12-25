package org.hzi.sormas.lbds.messaging;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.util.Base64;
import org.hzi.sormas.lbds.messaging.util.KeySerializationUtil;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import static org.hzi.sormas.lbds.messaging.Constants.*;

public class LbdsKexIntent extends android.content.Intent implements IntentTypeCarrying {

    LbdsKexIntent(Intent intent){
        super(ACTION_SEND);
        putExtras(intent);
    }

    protected LbdsKexIntent() {
        super(ACTION_SEND);
        putExtra(INTENT_TYPE, IntentTypeCarrying.toIntentType(this).toString());
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

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public void setAesSecret(String secret, PublicKey publicKey){
        byte[] enc = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            enc = cipher.doFinal(secret.getBytes(StandardCharsets.UTF_8));
            String encSecret = Base64.encodeToString(enc,0);
            putExtra(AES_KEY, encSecret);

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public String getAesSecret(PrivateKey privateKey){
        final String aesEncrypted = getStringExtra(AES_KEY);
        try {
            final Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            final byte[] plain = cipher.doFinal(Base64.decode(aesEncrypted, 0));
            return new java.lang.String(plain, StandardCharsets.UTF_8 );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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
