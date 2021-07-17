package com.corebyte.mob.kiipa.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.util.AppUtil;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class TrackExpireStockService extends IntentService {
    private static final String TAG = TrackExpireStockService.class.getSimpleName();

    public TrackExpireStockService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int days = AppUtil.getPreferenceSettings(getApplicationContext(),
                    getString(R.string.stock_expiration_days_number));
            Log.i(TAG, "-----------------ONSTART JOB---------------days-"+ days);
            TrackStock.trackExpireStockIn(this, days);
        }
    }

}
