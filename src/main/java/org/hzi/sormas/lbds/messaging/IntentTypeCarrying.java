package org.hzi.sormas.lbds.messaging;

import android.content.Intent;

import static org.hzi.sormas.lbds.messaging.Constants.INTENT_TYPE;

public interface IntentTypeCarrying {



    static IntentType toIntentType(Intent intent) {
        Class<? extends Intent> intentClass = intent.getClass();
        String fullName = intentClass.getName();
        if (fullName.equals(LbdsPropagateKexToSormasIntent.class.getName())) {
            return IntentType.KEX_TO_SORMAS_INTENT;
        } else if (fullName.equals(LbdsPropagateKexToLbdsIntent.class.getName())) {
            return IntentType.KEX_TO_LBDS_INTENT;
        } else if (fullName.equals(LbdsSendIntent.class.getName())) {
            return IntentType.HTTP_SEND_INTENT;
        } else if (fullName.equals(LbdsResponseIntent.class.getName())) {
            return IntentType.HTTP_RESPONSE_INTENT;
        } else {
            final String type = intent.getStringExtra(INTENT_TYPE);
            if (type == null || type.trim().isEmpty()) {
                return null;
            } else return IntentType.valueOf(type);
        }
    }

    static Intent toStrongTypedIntent(Intent intent){
        Intent result=null;
        switch (toIntentType(intent)){
            case HTTP_SEND_INTENT:
                result = new LbdsSendIntent(intent);
                break;
            case HTTP_RESPONSE_INTENT:
                result = new LbdsResponseIntent(intent);
                break;
            case KEX_TO_LBDS_INTENT:
                result = new LbdsPropagateKexToLbdsIntent(intent);
                break;
            case KEX_TO_SORMAS_INTENT:
                result = new LbdsPropagateKexToSormasIntent(intent);
                break;
            default:
        }
        return result;
    }
}
