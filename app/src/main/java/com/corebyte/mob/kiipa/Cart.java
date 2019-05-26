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

    private static final String TAG = Cart.class.getSimpleName();
    private Map mStockMeasures;
    private Map mMeasureCartStock;

    public Cart() {
        mStockMeasures = new HashMap<Stock, List<Measurement>>();
        mMeasureCartStock = new HashMap<Measurement, CartStock>();
    }

    public void add(Stock stock, Measurement measurement, int inputQty) {

        if (inputQty == 0) return;

        if (mStockMeasures.containsKey(stock)) {
            List<Measurement> stockmeasurements = (List<Measurement>) mStockMeasures.get(stock);
            if (stockmeasurements.contains(measurement)) {
                CartStock existingCartStock = (CartStock) mMeasureCartStock.get(measurement);
                existingCartStock.addQuantity(inputQty);
                existingCartStock.calculateCost();

            } else {
                stockmeasurements.add(measurement);
                //replace require API 24
                //this a workaround
                mStockMeasures.remove(stock);
                mStockMeasures.put(stock, stockmeasurements);

                //add to cartholder
                CartStock cartStock = new CartStock(stock.id, measurement.id, stock.getName(),
                        measurement.getName(), measurement.getSellingPrice());

                cartStock.addQuantity(inputQty);
                cartStock.calculateCost();
                mMeasureCartStock.put(measurement, cartStock);

            }
        } else {
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

    }

    public CartSummary getCartSummary() {
        ArrayList<CartStock> cartStocks = getCartStocks();
        CartSummary cartSummary = new CartSummary(cartStocks);
        return cartSummary;
    }


    public ArrayList<CartStock> getCartStocks() {
        ArrayList<CartStock> cartStocks = new ArrayList<>();
        Set<Measurement> keys = mMeasureCartStock.keySet();
        for (Measurement measurement : keys) {
            CartStock cartStock = (CartStock) mMeasureCartStock.get(measurement);
            cartStocks.add(cartStock);
        }
        return cartStocks;

    }


}
