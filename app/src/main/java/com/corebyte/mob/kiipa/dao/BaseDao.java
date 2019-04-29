package com.corebyte.mob.kiipa.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import com.corebyte.mob.kiipa.model.BaseModel;

import java.util.List;

public interface BaseDao<T extends BaseModel> {

    List<T> getAll();

    T findById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T entity);

    @Insert
    long[] insert(T... entities);


    @Update
    void update(T entity);

    @Delete
    void delete(T entity);


}
