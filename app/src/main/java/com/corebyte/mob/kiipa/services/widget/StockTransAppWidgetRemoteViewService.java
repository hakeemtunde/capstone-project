package com.corebyte.mob.kiipa.services.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class StockTransAppWidgetRemoteViewService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StockTransAppWidgetRemoteViewFactory(getApplicationContext(), intent);
    }
}
