package com.corebyte.mob.kiipa.event;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.model.BaseModel;
import com.corebyte.mob.kiipa.repo.CrudAsyncTask;
import com.corebyte.mob.kiipa.repo.CrudOperation;

public interface CrudDao<T extends BaseModel> extends CrudOperation<T> {
    BaseDao getCrudDao(AppDatabase database);
    public CrudAsyncTask getAsync();

}
