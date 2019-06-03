package com.corebyte.mob.kiipa.repo;

import android.content.Context;
import android.os.AsyncTask;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.dao.MeasurementDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.repo.CustomAsyncTask.AsyncCallback;
import com.corebyte.mob.kiipa.services.TrackStock;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MeasurementCrudOperation implements CrudDao<Measurement> {

    private final CrudAsyncTask<Measurement> mCrudAsync;

    public MeasurementCrudOperation(Context context) {
        mCrudAsync = new CrudAsyncTask<>(context, this);
    }

    @Override
    public BaseDao getCrudDao(AppDatabase database) {
        return database.measurementDao();
    }

    @Override
    public long create(Measurement model) {
        DateUtil.initCreateDate(model);
        return mCrudAsync.create(model);
    }

    @Override
    public long[] create(Measurement... models) {
        DateUtil.initCreateDate(models);
        return mCrudAsync.create(models);
    }

    @Override
    public void update(Measurement model) {
        DateUtil.modifyUpdateDate(model);
        mCrudAsync.update(model);
    }

    @Override
    public void update(Measurement... models) {
        DateUtil.modifyUpdateDate(models);
        mCrudAsync.update(models);
    }

    @Override
    public void delete(Measurement model) {
        mCrudAsync.delete(model);
    }

    @Override
    public Measurement getById(long id) {
        return mCrudAsync.getById(id);
    }

    @Override
    public List<Measurement> getAll() {
        return mCrudAsync.getAll();
    }

    public List<Measurement> findByStockId(long stockId) {

        List<Measurement> measurements = new ArrayList<>();

        AsyncCallback<Measurement> callback = new AsyncCallback<Measurement>() {
            @Override
            public List<Measurement> get(long id) {
                MeasurementDao measurementDao = (MeasurementDao) mCrudAsync.getDao();
                return measurementDao.findByStockId(id);
            }
        };

        CustomAsyncTask<Measurement> asyncCustom = new CustomAsyncTask<>(callback);
        asyncCustom.execute(stockId);

        try {
            measurements = asyncCustom.get();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        }


        return measurements;
    }

    public List<Measurement> getStockLevel(final TrackStock.TrackStockHandler handler,
                                           int level) {
        final List<Measurement> measurements = new ArrayList<>();

        new AsyncTask<Integer, Void, List<Measurement>>(){

            @Override
            protected List<Measurement> doInBackground(Integer... integers) {
                return ((MeasurementDao)mCrudAsync.getDao()).findStockLevel(integers[0]);
            }

            @Override
            protected void onPostExecute(List<Measurement> result) {
                measurements.addAll(result);

                handler.handler(result);
            }
        }.execute(level);

        return measurements;
    }
}
