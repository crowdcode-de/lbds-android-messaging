package org.hzi.sormas.lbds.messaging;

import android.content.Intent;

public interface IntentTypeCarrying {

    default IntentType toIntentType(Intent intent) {
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
        } else return null;
    }
}
