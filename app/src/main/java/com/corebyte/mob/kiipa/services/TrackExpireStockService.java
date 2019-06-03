package com.corebyte.mob.kiipa.services;

import android.app.IntentService;
import android.content.Intent;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class TrackExpireStockService extends IntentService {

    public static final String EXPIRE_DAYS_INTERVAL = "extra.DAYS_INTERVAL";

    public TrackExpireStockService() {
        super("TrackExpireStockService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
//            int daysInterval = intent.getIntExtra(EXPIRE_DAYS_INTERVAL, 0);
            TrackStock.trackExpireStockIn(this, 10);
        }
    }

}
