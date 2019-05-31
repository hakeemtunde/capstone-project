package com.corebyte.mob.kiipa.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.JOB_SCHEDULER_SERVICE;

public class StockExpirationScheduler {

    private static final int JOB_ID = 100;

    public static void stockExpirationJobSheduler(Context context, boolean enabled) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
//            PersistableBundle extras = new PersistableBundle();
//            extras.putInt(TrackStock.EXPIRE_DAYS_INTERVAL, 2);

            if (enabled) {
                ComponentName componentName = new ComponentName(context, TrackExpireStockJobService.class);
                JobInfo jobInfo = new JobInfo.Builder(JOB_ID, componentName)
                        .setRequiresDeviceIdle(true)
                        .setPeriodic(AlarmManager.INTERVAL_DAY)
                        .build();

                jobScheduler.schedule(jobInfo);

            } else {
                jobScheduler.cancel(JOB_ID);

            }
        } else {
            setUpAlarmService(context, enabled);
        }
    }

    private static void setUpAlarmService(Context context, boolean enabled) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(context, TrackExpireStockReceiver.class);
        intent.setAction(TrackExpireStockReceiver.TRIGGER_STOCK_EXPIRY_TRACKER);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        if (enabled) {

            alarmManager.set(AlarmManager.ELAPSED_REALTIME,
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }

    }
}
