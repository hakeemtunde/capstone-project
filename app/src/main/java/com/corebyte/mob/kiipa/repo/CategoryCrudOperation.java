package com.corebyte.mob.kiipa.repo;

import android.content.Context;
import android.os.AsyncTask;

import com.corebyte.mob.kiipa.adapter.AdapterDataLoader;
import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.model.BaseModel;
import com.corebyte.mob.kiipa.model.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CategoryCrudOperation implements CrudOperation<Category> {

    private final AppDatabase mAppDatabase;

    public CategoryCrudOperation(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
    }

    @Override
    public void create(Category model) {
        new AsyncTask<BaseModel, Void, Void>() {
            @Override
            protected Void doInBackground(BaseModel... params) {
                Category category = (Category) params[0];
                mAppDatabase.categoryDao().insert(category);
                return null;
            }
        }.execute(model);
    }

    @Override
    public void update(Category category) {
        category.updatedAt = new Date();

        new AsyncTask<BaseModel, Void, Void>() {
            @Override
            protected Void doInBackground(BaseModel... params) {
                Category category = (Category) params[0];
                mAppDatabase.categoryDao().update(category);
                return null;
            }
        }.execute(category);
    }

    @Override
    public void delete(Category model) {
        Category category = getById(model.id);
        if (category == null) return;

        new AsyncTask<BaseModel, Void, Void>() {

            @Override
            protected Void doInBackground(BaseModel... params) {
                Category category = (Category) params[0];
                mAppDatabase.categoryDao().delete(category);
                return null;
            }
        }.execute(category);
    }

    @Override
    public Category getById(int id) {
        Category category = null;
        AsyncTask asyncTask = new AsyncTask<Integer, Void, Category>() {
            @Override
            protected Category doInBackground(Integer... ids) {
                return mAppDatabase.categoryDao().findById(ids[0]);

            }
        }.execute(id);

        try {
            category = ((Category) asyncTask.get());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        }

        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> data = new ArrayList<>();
        AsyncTask asyncTask = new AsyncTask<Void, Void, List<Category>>() {

            @Override
            protected List<Category> doInBackground(Void... voids) {
                List<Category> data = mAppDatabase.categoryDao().getAll();
                return data;
            }

        }.execute();

        try {
            data = ((List<Category>) asyncTask.get());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        }

        return data;

    }

    public void loadDataToAdapter(AdapterDataLoader loader) {
        List<Category> data = getAll();
        loader.loadData(data);
    }
}
