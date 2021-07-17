package com.corebyte.mob.kiipa.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.util.AppUtil;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TrackExpireStockJobService extends JobService {

    private static final String TAG = TrackExpireStockJobService.class.getSimpleName();

    public TrackExpireStockJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        int days = AppUtil.getPreferenceSettings(getApplicationContext(),
                getString(R.string.stock_expiration_days_number));
        Log.i(TAG, "days: "+ days);
        TrackStock.trackExpireStockIn(getApplicationContext(), days);
        jobFinished(jobParameters, false);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


}
