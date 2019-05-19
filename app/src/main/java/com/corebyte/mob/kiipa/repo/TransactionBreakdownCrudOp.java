package com.corebyte.mob.kiipa.repo;

import android.content.Context;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.TransactionBreakdown;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.util.List;

public class TransactionBreakdownCrudOp implements CrudDao<TransactionBreakdown> {

    private final CrudAsyncTask<TransactionBreakdown> mCrudAsync;

    public TransactionBreakdownCrudOp(Context context) {
        mCrudAsync = new CrudAsyncTask<>(context, this);
    }

    @Override
    public BaseDao getCrudDao(AppDatabase database) {
        return database.transactionBreakdownDao();
    }

    @Override
    public long create(TransactionBreakdown model) {
        DateUtil.initCreateDate(model);
        return mCrudAsync.create(model);
    }

    @Override
    public long[] create(TransactionBreakdown... models) {
        DateUtil.initCreateDate(models);
        return mCrudAsync.create(models);
    }

    @Override
    public void update(TransactionBreakdown model) {
        DateUtil.modifyUpdateDate(model);
        mCrudAsync.update(model);
    }

    @Override
    public void update(TransactionBreakdown... models) {
        DateUtil.modifyUpdateDate(models);
        mCrudAsync.update(models);
    }

    @Override
    public void delete(TransactionBreakdown model) {
        mCrudAsync.delete(model);
    }

    @Override
    public TransactionBreakdown getById(long id) {
        return mCrudAsync.getById(id);
    }

    @Override
    public List<TransactionBreakdown> getAll() {
        return mCrudAsync.getAll();
    }
}
