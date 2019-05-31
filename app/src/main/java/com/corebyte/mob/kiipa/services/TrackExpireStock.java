package com.corebyte.mob.kiipa.services;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.repo.StockCrudOperation;

import java.util.List;

public class TrackExpireStock {

    public static final String EXPIRE_DAYS_INTERVAL = "extra.DAYS_INTERVAL";
//    private Context mContext;
//    private StockCrudOperation mStockCrudOperation;
//
//    public TrackExpireStock(Context context) {
//        mContext = context;
//        mStockCrudOperation = new StockCrudOperation(context);
//    }

    public static void trackExpireStockIn(Context context, int days) {

        StockCrudOperation operation = new StockCrudOperation(context);
        List<Stock> expireStocks = operation.findExpireStockIn2(days, true);

    }

    public static void notification(Context context, List<Stock> expirestocks) {

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "Channel001")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Expire Products")
                .setContentText("following product will expire in 10 days" + expirestocks.toString())
                .setPriority(NotificationCompat.DEFAULT_ALL);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(001, notification.build());

    }

}
