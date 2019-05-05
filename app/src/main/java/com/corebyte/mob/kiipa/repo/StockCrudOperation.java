package com.corebyte.mob.kiipa.repo;

import android.content.Context;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.util.List;

public class StockCrudOperation implements CrudDao<Stock> {

    private CrudAsyncTask<Stock> mCrudAsync;

    public StockCrudOperation(Context context) {
        mCrudAsync = new CrudAsyncTask<>(context, this);
    }

    @Override
    public BaseDao getCrudDao(AppDatabase database) {
        return database.stockDao();
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
    public List<Stock> getAll() {
        return mCrudAsync.getAll();
    }
}
