package com.corebyte.mob.kiipa.repo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.Category;

import java.util.List;

@Dao
public interface CategoryDao extends BaseDao<Category> {

    @Override
    @Query(AppQuery.CATEGORY_FETCH_ALL)
    List<Category> getAll();

    @Override
    @Query(AppQuery.CATEGORY_FETCH_BY_ID)
    Category findById(int id);

}
