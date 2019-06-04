package com.corebyte.mob.kiipa.event;

import android.content.Context;

import java.util.List;

public interface EventHandler<T> {

    void create(String... params);

    void update(T model);

    void delete(T model);

    void onEditButtonClicked(T model);

    public Context getContext();

}
