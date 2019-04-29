package com.corebyte.mob.kiipa.repo;

import android.content.Context;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.Measurement;

import java.util.List;

public class MeasurementCrudOperation implements CrudDao<Measurement> {

    private final CrudAsyncTask<Measurement> mCrudAsync;

    public MeasurementCrudOperation(Context context) {
        mCrudAsync = new CrudAsyncTask<>(context, this);
    }

    @Override
    public BaseDao getDao(AppDatabase database) {
        return database.measurementDao();
    }

    @Override
    public long create(Measurement model) {
        return mCrudAsync.create(model);
    }

    @Override
    public long[] create(Measurement... models) {
        return mCrudAsync.create(models);
    }

    @Override
    public void update(Measurement model) {
        mCrudAsync.update(model);
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
}
