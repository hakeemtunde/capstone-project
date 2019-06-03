package com.corebyte.mob.kiipa.services.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.corebyte.mob.kiipa.R;

/**
 * Implementation of App Widget functionality.
 */
public class StockTransactionAppWidgetProvider extends AppWidgetProvider {

    private static final String TAG = StockTransactionAppWidgetProvider.class.getSimpleName();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(),
                R.layout.stock_transaction_app_widget);

        Intent intent = new Intent(context, StockTransAppWidgetRemoteViewService.class);
        views.setRemoteAdapter(appWidgetId, R.id.widget_app_list_view, intent);
        views.setEmptyView(R.id.widget_app_list_view, R.id.tv_no_transactions);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        super.onReceive(context, intent);
    }
}

