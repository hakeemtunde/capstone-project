package com.corebyte.mob.kiipa.services;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.repo.MeasurementCrudOperation;
import com.corebyte.mob.kiipa.repo.StockCrudOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrackStock {

    public static final String EXPIRE_DAYS_INTERVAL = "extra.DAYS_INTERVAL";
    private static final String TAG = TrackStock.class.getSimpleName();
//    private Context mContext;
//    private StockCrudOperation mStockCrudOperation;
//
//    public TrackStock(Context context) {
//        mContext = context;
//        mStockCrudOperation = new StockCrudOperation(context);
//    }

    public static void trackExpireStockIn(Context context, int days) {

        StockCrudOperation operation = new StockCrudOperation(context);
        List<Stock> expireStocks = operation.findExpireStockIn2(days, true);

    }

    public static void trackStockLevel(final Context context, int level) {
        MeasurementCrudOperation operation = new MeasurementCrudOperation(context);
        final StockCrudOperation stockCrudOperation = new StockCrudOperation(context);

        //handler
        TrackStockHandler trackStockHandler = new TrackStockHandler<Measurement>() {
            @Override
            public void handler(List<Measurement> items) {
                Log.i(TAG, "low stocks: " + items.toString());
                Map<Stock, List<Measurement>> stockMeasurementMapList = getStockMeasurementMapList(
                        stockCrudOperation, items);

                if (!stockMeasurementMapList.isEmpty()) {
                    List<String> stockLevels = extractStockInfo(stockMeasurementMapList);
                    stockLevelNotification(context, stockLevels);
                }

            }
        };

        operation.getStockLevel(trackStockHandler, level);

    }

    private static Map<Stock, List<Measurement>> getStockMeasurementMapList(
            StockCrudOperation stockCrudOperation, List<Measurement> items) {
        Map<Stock, List<Measurement>> stockListMap = new HashMap<>();

        for (Measurement measurement : items) {
            Stock stock = stockCrudOperation.getById(measurement.getStockId());

            if (!stockListMap.containsKey(stock)) {
                List<Measurement> ms = new ArrayList<>();
                ms.add(measurement);
                stockListMap.put(stock, ms);
            } else {
                stockListMap.get(stock).add(measurement);
            }
        }

        return stockListMap;
    }

    private static List<String> extractStockInfo(Map<Stock, List<Measurement>> stockMeasureListEntry) {
        List<String> stockLevelsInfo = new ArrayList<>();

        Set<Map.Entry<Stock, List<Measurement>>> entrySets = stockMeasureListEntry.entrySet();

        for (Map.Entry<Stock, List<Measurement>> entry : entrySets) {
            Stock stock = entry.getKey();

            StringBuilder sb = new StringBuilder(stock.getName());
            sb.append(": ");

            for (Measurement measurement : entry.getValue()) {

                sb.append(measurement.getName());
                sb.append(", ");
            }

            stockLevelsInfo.add(sb.toString());
        }

        return stockLevelsInfo;
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

    public static void stockLevelNotification(Context context, List<String> stocks) {

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "Channel001")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Low Stock")
                .setContentText("The following stocks quantity level are below 20" + stocks.toString())
                .setPriority(NotificationCompat.DEFAULT_ALL);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(001, notification.build());

    }

    public interface TrackStockHandler<T> {
        public void handler(List<T> items);
    }

}
