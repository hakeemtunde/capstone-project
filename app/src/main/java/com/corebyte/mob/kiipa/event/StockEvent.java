package com.corebyte.mob.kiipa.event;

import android.content.Intent;

import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.model.Stock;

public interface StockEvent {

    void onClick(Stock stock);

    void onCartClick(Stock stock);

    void onAddToCart(Measurement measurement, int qty);

}
