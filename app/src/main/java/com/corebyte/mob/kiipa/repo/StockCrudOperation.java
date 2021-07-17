package com.corebyte.mob.kiipa.repo;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.dao.StockDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.services.TrackStock;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class StockCrudOperation implements CrudDao<Stock> {

    private static final String TAG = StockCrudOperation.class.getSimpleName();

    private CrudAsyncTask<Stock> mCrudAsync;

    public StockCrudOperation(Context context) {
        mCrudAsync = new CrudAsyncTask<>(context, this);
    }

    @Override
    public BaseDao getCrudDao(AppDatabase database) {
        return database.stockDao();
    }

    @Override
    public CrudAsyncTask getAsync() {
        return mCrudAsync;
    }

    @Override
    public long create(Stock model) {
        DateUtil.initCreateDate(model);
        return mCrudAsync.create(model);
    }

    @Override
    public long[] create(Stock... models) {
        DateUtil.initCreateDate(models);
        return mCrudAsync.create(models);
    }

    @Override
    public void update(Stock model) {
        DateUtil.modifyUpdateDate(model);
        mCrudAsync.update(model);
    }

    @Override
    public void update(Stock... models) {
        DateUtil.modifyUpdateDate(models);
        mCrudAsync.update(models);
    }

    @Override
    public void delete(Stock model) {
        mCrudAsync.delete(model);
    }

    @Override
    public Stock getById(long id) {
        return mCrudAsync.getById(id);
    }

    @Override
    public List<Stock> getAllRecord() {
        return mCrudAsync.getAllRecord();
    }

    public List<Stock> getExpireStockIn(int days) {

        final List<Stock> expireStocks = new ArrayList<>();

        AsyncTask asyncTask = new AsyncTask<Integer, Void, List<Stock>>() {

            @Override
            protected List<Stock> doInBackground(Integer... integers) {
                return ((StockDao) mCrudAsync.getDao()).findExpireStockIn(integers[0]);
            }

            @Override
            protected void onPostExecute(List<Stock> stocks) {
                expireStocks.addAll(stocks);
                Log.i(TAG, " expire stocks " + expireStocks.toString());
            }
        }.execute(days);


        return expireStocks;

    }

//    public List<Stock> findExpireStockIn2(int days, final boolean shownotification) {
//
//        final List<Stock> expireStocks = new ArrayList<>();
//
//        AsyncTask asyncTask = new AsyncTask<Integer, Void, List<Stock>>() {
//
//            @Override
//            protected List<Stock> doInBackground(Integer... integers) {
//                return ((StockDao) mCrudAsync.getDao()).findExpireStockIn(integers[0]);
//            }
//
//            @Override
//            protected void onPostExecute(List<Stock> stocks) {
//                expireStocks.addAll(stocks);
//                if (shownotification && !expireStocks.isEmpty()) {
//                    TrackStock.notification(mCrudAsync.getContext(), expireStocks);
//                }
//                Log.i(TAG, " expire stocks " + expireStocks.toString());
//            }
//        }.execute(days);
//
//
//        return expireStocks;
//
//    }


    public void findExpireStockAndTriggerAlarmService(final TrackStock.TrackStockHandler handler,
            int days) {

        final List<Stock> expireStocks = new ArrayList<>();

        new AsyncTask<Integer, Void, List<Stock>>() {

            @Override
            protected List<Stock> doInBackground(Integer... integers) {
                return ((StockDao) mCrudAsync.getDao()).findExpireStockIn(integers[0]);
            }

            @Override
            protected void onPostExecute(List<Stock> stocks) {
                expireStocks.addAll(stocks);
                handler.handler(stocks);
                Log.i(TAG, " expire stocks " + expireStocks.toString());
            }
        }.execute(days);

    }


}
