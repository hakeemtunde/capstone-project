package com.corebyte.mob.kiipa.services.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.TransactionSummary;
import com.corebyte.mob.kiipa.repo.TransactionSummaryCrudOp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockTransAppWidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = StockTransAppWidgetRemoteViewFactory.class.getSimpleName();
    private Context mContext;
    private TransactionSummaryCrudOp crudOp;
    private List<TransactionSummary> transactionSummaryList;

    public StockTransAppWidgetRemoteViewFactory(Context context, Intent intent) {
        mContext = context;
        crudOp = new TransactionSummaryCrudOp(context);
        transactionSummaryList = crudOp.getTransactionsByDate(new Date());
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return transactionSummaryList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        if (position == AdapterView.INVALID_POSITION) return null;

        String salesOrder = "Order " + String.valueOf(transactionSummaryList.get(position).getSalesOrder());
        String salesTotal = String.valueOf(transactionSummaryList.get(position).getTotal());

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_transaction_list);
        remoteViews.setTextViewText(R.id.widget_sales_order_tv, salesOrder);
        remoteViews.setTextViewText(R.id.widget_sales_total_tv, salesTotal);

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
