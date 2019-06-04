package com.corebyte.mob.kiipa.event;

import com.corebyte.mob.kiipa.model.BaseModel;

import java.util.List;

public interface AdapterAction<T extends BaseModel> {

    void setAdapterData(List<T> data);
}
