package com.corebyte.mob.kiipa.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TrackExpireStockReceiver extends BroadcastReceiver {

    public static final String TRIGGER_STOCK_EXPIRY_TRACKER = "STOCK_EXPIRY_TRACKER";
    private static final String TAG = TrackExpireStockReceiver.class.getSimpleName() ;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(TRIGGER_STOCK_EXPIRY_TRACKER)) {
            Log.i(TAG, "TRIGGER ALARM TRACKER");
            Intent intent1 = new Intent(context, TrackExpireStockService.class);
            context.startService(intent1);

        }
    }
}
