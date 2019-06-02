package com.corebyte.mob.kiipa.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TrackExpireStockReceiver extends BroadcastReceiver {

    public static final String TRIGGER_STOCK_EXPIRY_TRACKER = "STOCK_EXPIRY_TRACKER";
    public static final String TRIGGER_LOW_STOCK = "LOW_STOCK_ACTION";

    private static final String TAG = TrackExpireStockReceiver.class.getSimpleName() ;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(TRIGGER_STOCK_EXPIRY_TRACKER)) {
            Log.i(TAG, "TRIGGER ALARM TRACKER");
            Intent intent1 = new Intent(context, TrackExpireStockService.class);
            context.startService(intent1);

        }

        if (intent != null && intent.getAction().equals(TRIGGER_LOW_STOCK)) {
            Log.i(TAG, "LOW STOCK LEVEL TRIGGER ALARM TRACKER");
            Intent intent1 = new Intent(context, LowStockIntentService.class);
            context.startService(intent1);
        }
    }
}
