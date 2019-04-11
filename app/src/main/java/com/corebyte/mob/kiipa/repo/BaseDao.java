package com.corebyte.mob.kiipa.repo;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import java.util.List;

public interface BaseDao<T> {

    List<T> getAll();

    T findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T entity);

    @Update
    void update(T entity);

    @Delete
    void delete(T entity);


}
