package com.corebyte.mob.kiipa.repo;

import android.content.Context;
import android.util.Log;

import com.corebyte.mob.kiipa.adapter.AdapterDataLoader;
import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.Category;

import java.util.Date;
import java.util.List;

public class CategoryCrudOperation implements CrudDao<Category> {

    private final CrudAsyncTask<Category> mCrudAsyncTask;
    private List<Category> mCategories;

    public CategoryCrudOperation(Context context) {
        mCrudAsyncTask = new CrudAsyncTask<>(context, this);
    }

    public String[] getAllAsArray() {

        if (mCategories == null)  {
            mCategories = getAll();
        }

        String[] categoryArray = new String[mCategories.size()];

        for (int i = 0; i < mCategories.size(); i++) {
            Category category = mCategories.get(i);
            categoryArray[i] = category.getName();
        }
        return categoryArray;
    }

    public Category getCategoryWithIndex(int index) {
        if (mCategories == null)  {
            mCategories = getAll();
        }

        if (index > (mCategories.size() - 1)) return null;

        return mCategories.get(index);
    }

    @Override
    public long create(Category model) {
        long id = mCrudAsyncTask.create(model);
        Log.i(this.getClass().getSimpleName(), "ID: "+ id);
        return id;
    }

    @Override
    public long[] create(Category... models) {
        return mCrudAsyncTask.create(models);
    }

    @Override
    public void update(Category category) {
        category.updatedAt = new Date();
        mCrudAsyncTask.update(category);
    }

    @Override
    public void delete(Category model) {
        mCrudAsyncTask.delete(model);
    }

    @Override
    public Category getById(long id) {
        Category category = mCrudAsyncTask.getById(id);
        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> data = mCrudAsyncTask.getAll();
        return data;
    }

    @Override
    public BaseDao getDao(AppDatabase database) {
        return database.categoryDao();
    }

    public void loadDataToAdapter(AdapterDataLoader loader) {
        List<Category> data = getAll();
        loader.loadData(data);
    }

}
