package com.corebyte.mob.kiipa.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.Category;
import com.corebyte.mob.kiipa.repo.AppQuery;

import java.util.List;

@Dao
public interface CategoryDao extends BaseDao<Category> {

    @Override
    @Query(AppQuery.CATEGORY_FETCH_ALL)
    LiveData<List<Category>> getAll();

    @Override
    @Query(AppQuery.CATEGORY_FETCH_BY_ID)
    Category findById(long id);

    @Override
    @Query(AppQuery.CATEGORY_FETCH_ALL)
    List<Category> getAllRecords();
}
