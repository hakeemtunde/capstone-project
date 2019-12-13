package com.corebyte.mob.kiipa.repo;

import android.content.Context;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.CreditorsPaymentLog;

import java.util.List;

public class CreditorsPaymentLogCrudOperation implements CrudDao<CreditorsPaymentLog> {

    private CrudAsyncTask<CreditorsPaymentLog> mCrudAsyncTask;
    private List<CreditorsPaymentLog> creditorsPaymentLogList;

    public CreditorsPaymentLogCrudOperation(Context context) {
        mCrudAsyncTask = new CrudAsyncTask<>(context, this);
    }


    @Override
    public BaseDao getCrudDao(AppDatabase database) {
        return database.creditorsPaymentLogDao();
    }

    @Override
    public CrudAsyncTask getAsync() {
        return mCrudAsyncTask;
    }

    @Override
    public long create(CreditorsPaymentLog model) {
        long id = mCrudAsyncTask.create(model);
        return id;
    }

    @Override
    public long[] create(CreditorsPaymentLog... models) {
        return null;
    }

    @Override
    public void update(CreditorsPaymentLog model) {
        mCrudAsyncTask.update(model);
    }

    @Override
    public void update(CreditorsPaymentLog... models) {}

    @Override
    public void delete(CreditorsPaymentLog model) {
        mCrudAsyncTask.delete(model);
    }

    @Override
    public CreditorsPaymentLog getById(long id) {
        return mCrudAsyncTask.getById(id);
    }

    @Override
    public List<CreditorsPaymentLog> getAllRecord() {
        return mCrudAsyncTask.getAllRecord();
    }
}
