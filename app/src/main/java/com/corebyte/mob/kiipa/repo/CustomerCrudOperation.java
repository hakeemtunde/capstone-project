package com.corebyte.mob.kiipa.repo;

import android.content.Context;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerCrudOperation implements CrudDao<Customer> {

    private CrudAsyncTask<Customer> mCrudAsync;

    public CustomerCrudOperation(Context context) {
        mCrudAsync = new CrudAsyncTask<>(context, this);
    }

    @Override
    public BaseDao getCrudDao(AppDatabase database) {
        return database.customerDao();
    }

    @Override
    public long create(Customer model) {
        DateUtil.initCreateDate(model);
        return mCrudAsync.create(model);
    }

    @Override
    public long[] create(Customer... models) {
        DateUtil.initCreateDate(models);
        return mCrudAsync.create(models);
    }

    @Override
    public void update(Customer model) {
        DateUtil.modifyUpdateDate(model);
        mCrudAsync.update(model);

    }

    @Override
    public void update(Customer... models) {
        DateUtil.modifyUpdateDate(models);
        mCrudAsync.update(models);
    }

    @Override
    public void delete(Customer model) {
        mCrudAsync.delete(model);
    }

    @Override
    public Customer getById(long id) {
        return mCrudAsync.getById(id);
    }

    @Override
    public List<Customer> getAll() {
        return mCrudAsync.getAll();
    }
}
