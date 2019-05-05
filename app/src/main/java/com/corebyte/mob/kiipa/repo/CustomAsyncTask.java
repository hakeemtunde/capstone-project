package com.corebyte.mob.kiipa.repo;

import android.os.AsyncTask;

import com.corebyte.mob.kiipa.model.BaseModel;

import java.util.List;

public class CustomAsyncTask<T> extends AsyncTask<Long, Void, List<T>> {


    private AsyncCallback callback;

    public CustomAsyncTask(AsyncCallback cb) {
        this.callback = cb;
    }

    @Override
    protected List<T> doInBackground(Long... longs) {
        return callback.get(longs[0]);
    }


    public interface AsyncCallback<T extends BaseModel> {

        List<T> get(long id);

    }
}
