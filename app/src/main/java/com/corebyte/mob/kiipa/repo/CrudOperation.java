package com.corebyte.mob.kiipa.repo;

import com.corebyte.mob.kiipa.model.BaseModel;

import java.util.List;

public interface CrudOperation<T extends BaseModel> {

    void create(T model);

    void update(T model);

    void delete(T model);

    BaseModel getById(int id);

    List<T> getAll();
}
