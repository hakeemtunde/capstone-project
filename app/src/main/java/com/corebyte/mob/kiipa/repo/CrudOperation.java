package com.corebyte.mob.kiipa.repo;

import android.arch.lifecycle.LiveData;

import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.model.BaseModel;

import java.util.List;

public interface CrudOperation<T extends BaseModel> {

    long create(T model);

    long[] create(T... models);

    void update(T model);

    void update(T ... models);

    void delete(T model);

    T getById(long id);

//    LiveData<List<T>> getAll();

    List<T> getAllRecord();
}
