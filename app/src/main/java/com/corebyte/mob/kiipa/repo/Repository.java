package com.corebyte.mob.kiipa.repo;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.corebyte.mob.kiipa.adapter.AdapterDataLoader;
import com.corebyte.mob.kiipa.model.Category;

import java.util.List;

public class Repository {

    LiveData<List<Category>> data;
    private AppDatabase appDatabase;
    private AdapterDataLoader mAdapterDataLoader;

    public Repository(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    public Repository(Context context, AdapterDataLoader adapterData) {
        appDatabase = AppDatabase.getInstance(context);
        mAdapterDataLoader = adapterData;
    }

    public void insert(Category category) {
        new AsyncTask<Category, Void, Void>() {
            @Override
            protected Void doInBackground(Category... params) {
                appDatabase.categoryDao().insert(params[0]);
                return null;
            }
        }.execute(category);
    }

    public LiveData<List<Category>> getAll() {

        new AsyncTask<Void, Void, List<Category>>() {

            @Override
            protected List<Category> doInBackground(Void... voids) {
                List<Category> data = appDatabase.categoryDao().getAll();
                System.out.println("Data: " + data.size() + " d: " + data.get(0).createdAt + " " + data.get(0).getName());
                return data;
            }

            @Override
            protected void onPostExecute(List<Category> categories) {
                super.onPostExecute(categories);
                mAdapterDataLoader.loadData(categories);
            }
        }.execute();


        return data;
    }


//    public interface LoadDataEvent {
//        public void setAdapterData(AdapterDataLoader<Category> adapter);
//
//        class LoadDataEventImp implements LoadDataEvent {
//
//            @Override
//            public void setAdapterData(AdapterDataLoader<Category> adapter) {
//                adapter.loadData();
//            }
//
//
//        }
//    }


}
