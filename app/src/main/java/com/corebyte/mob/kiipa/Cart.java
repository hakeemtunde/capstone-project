package com.corebyte.mob.kiipa;

import android.util.Log;

import com.corebyte.mob.kiipa.model.CartStock;
import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.model.Stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cart {

    private Map mStockMeasures;
    private Map mMeasureCartStock;

    public Cart() {
        mStockMeasures = new HashMap<Stock, List<Measurement>>();
        mMeasureCartStock  = new HashMap<Measurement, CartStock>();
    }

    public void add(Stock stock, Measurement measurement, int inputQty) {

        if(inputQty == 0) return;

        if (mStockMeasures.containsKey(stock)) {
            List<Measurement> stockmeasurements = (List<Measurement>) mStockMeasures.get(stock);
            if (stockmeasurements.contains(measurement)) {
                CartStock existingCartStock = (CartStock) mMeasureCartStock.get(measurement);
                existingCartStock.addQuantity(inputQty);
                existingCartStock.calculateCost();

            } else {
                stockmeasurements.add(measurement);
                mStockMeasures.remove(stock);
                mStockMeasures.put(stock, stockmeasurements);

                //add to cartholder
                CartStock cartStock = new CartStock(stock.id, measurement.id, stock.getName(),
                        measurement.getName(), measurement.getSellingPrice());

                cartStock.addQuantity(inputQty);
                cartStock.calculateCost();
                mMeasureCartStock.put(measurement, cartStock);

            }
        }else {
            //first time adding stock
            List<Measurement> measurements = new ArrayList<>();
            measurements.add(measurement);
            mStockMeasures.put(stock, measurements);

            //add to cartholder
            CartStock cartStock = new CartStock(stock.id, measurement.id, stock.getName(),
                    measurement.getName(), measurement.getSellingPrice());

            cartStock.addQuantity(inputQty);
            cartStock.calculateCost();
            mMeasureCartStock.put(measurement, cartStock);
        }

        Set set = mMeasureCartStock.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            CartStock cartStock = (CartStock) mMeasureCartStock.get(iterator.next());
                    Log.i(this.getClass().getSimpleName(), " "
                + "\n CartStock: "+ cartStock.toString());
        }
    }
}
