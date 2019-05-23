package com.corebyte.mob.kiipa.repo;

import android.content.Context;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.dao.CreditorsTransactionDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.CreditorsTransaction;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.util.Date;
import java.util.List;

public class CreditorsTransactionCrudOp implements CrudDao<CreditorsTransaction> {

    private CrudAsyncTask<CreditorsTransaction> mCrudAsync;

    public CreditorsTransactionCrudOp(Context context) {
        mCrudAsync = new CrudAsyncTask<>(context, this);
    }

    @Override
    public BaseDao getCrudDao(AppDatabase database) {
        return database.creditorsTransactionDao();
    }

    @Override
    public long create(CreditorsTransaction model) {
        DateUtil.initCreateDate(model);
        return mCrudAsync.create(model);
    }

    @Override
    public long[] create(CreditorsTransaction... models) {
        DateUtil.initCreateDate(models);
        return mCrudAsync.create(models);
    }

    @Override
    public void update(CreditorsTransaction model) {
        DateUtil.modifyUpdateDate(model);
        mCrudAsync.update(model);
    }

    @Override
    public void update(CreditorsTransaction... models) {
        DateUtil.modifyUpdateDate(models);
        mCrudAsync.update(models);
    }

    @Override
    public void delete(CreditorsTransaction model) {
        mCrudAsync.delete(model);
    }

    @Override
    public CreditorsTransaction getById(long id) {
        return mCrudAsync.getById(id);
    }

    @Override
    public List<CreditorsTransaction> getAll() {
        return mCrudAsync.getAll();
    }

    public int countCreditors(Date date) {
        return ((CreditorsTransactionDao)mCrudAsync.getDao()).countCreditorsTransactionByDate(date);
    }

    public List<CreditorsTransaction> findCreditorsByDate(Date date) {
        return ((CreditorsTransactionDao)mCrudAsync.getDao()).findByDate(date);
    }

}
