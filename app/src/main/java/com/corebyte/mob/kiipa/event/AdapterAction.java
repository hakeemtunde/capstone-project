package com.corebyte.mob.kiipa.event;

import com.corebyte.mob.kiipa.model.BaseModel;

public interface AdapterAction<T extends BaseModel> {

    void appendModel(T model);

    void refreshAdapter();
}
