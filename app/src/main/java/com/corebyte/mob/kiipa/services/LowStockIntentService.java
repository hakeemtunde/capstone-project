package com.corebyte.mob.kiipa.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class LowStockIntentService extends IntentService {

    public LowStockIntentService() {
        super(LowStockIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            //default level = 50
            TrackStock.trackStockLevel(getApplicationContext(), 50);
        }

    }
}
