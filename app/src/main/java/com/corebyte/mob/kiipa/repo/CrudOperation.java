package com.corebyte.mob.kiipa.repo;

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

    List<T> getAll();
}
