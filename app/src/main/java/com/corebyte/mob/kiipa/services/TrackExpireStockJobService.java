package com.corebyte.mob.kiipa.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.util.AppUtil;

public class TrackExpireStockJobService extends JobService {

    private static final String TAG = TrackExpireStockJobService.class.getSimpleName();

    public TrackExpireStockJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i(TAG, "-----------------ONSTART JOB----------------");
        //AppUtil.getPreferenceSettings(getApplicationContext(), getString(R.string.key_notify_on_expire_stock), false);
        TrackExpireStock.trackExpireStockIn(getApplicationContext(), 10);
        jobFinished(jobParameters, false);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


}
