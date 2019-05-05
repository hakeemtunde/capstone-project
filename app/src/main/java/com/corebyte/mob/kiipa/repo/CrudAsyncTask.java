package com.corebyte.mob.kiipa.repo;

import android.content.Context;
import android.os.AsyncTask;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.BaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CrudAsyncTask<T extends BaseModel> implements CrudOperation<T> {

    private final AppDatabase mAppDatabase;
    private BaseDao mDao;

    public CrudAsyncTask(Context context, CrudDao crudDao) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = crudDao.getCrudDao(mAppDatabase);
    }

    public BaseDao getDao() {
        return mDao;
    }

    @Override
    public long create(T model) {

        AsyncTask asyncTask = new AsyncTask<T, Void, Long>() {
            @Override
            protected Long doInBackground(T... models) {
                T model = models[0];
                long id = mDao.insert(model);
                return id;
            }
        }.execute(model);

        long id = 0;

        try {
            id = ((long) asyncTask.get());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        }

        return id;

    }

    @Override
    public long[] create(T... models) {

        AsyncTask asyncTask = new AsyncTask<T, Void, long[]>() {


            @Override
            protected long[] doInBackground(T... models) {
                long[] ids = mDao.insert(models);
                return ids;
            }
        }.execute(models);

        long ids[] = new long[models.length];

        try {
            ids = ((long[]) asyncTask.get());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        }

        return ids;

    }

    @Override
    public void update(T model) {

        new AsyncTask<T, Void, Void>() {
            @Override
            protected Void doInBackground(T... params) {
                T model = params[0];
                mDao.update(model);
                return null;
            }
        }.execute(model);
    }

    @Override
    public void update(T... models) {

        new AsyncTask<T, Void, Void>(){

            @Override
            protected Void doInBackground(T... ts) {
                mDao.update(ts);
                return null;
            }
        }.execute(models);
    }

    @Override
    public void delete(final T model) {

        T delmodel = getById(model.id);
        if (delmodel == null) return;

        new AsyncTask<T, Void, Void>() {

            @Override
            protected Void doInBackground(T... params) {
                mDao.delete(params[0]);
                return null;
            }
        }.execute(delmodel);

    }

    @Override
    public T getById(long id) {

        T model = null;

        AsyncTask asyncTask = new AsyncTask<Long, Void, T>() {
            @Override
            protected T doInBackground(Long... ids) {
                return (T) mDao.findById(ids[0]);
            }
        }.execute(id);

        try {
            model = ((T) asyncTask.get());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        }

        return model;
    }

    @Override
    public List<T> getAll() {

        List<T> data = new ArrayList<>();
        AsyncTask asyncTask = new AsyncTask<Void, Void, List<T>>() {

            @Override
            protected List<T> doInBackground(Void... voids) {
                List<T> data = mDao.getAll();
                return data;
            }

        }.execute();

        try {
            data = ((List<T>) asyncTask.get());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        }

        return data;

    }
}
