package com.corebyte.mob.kiipa.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.corebyte.mob.kiipa.dao.StockDao;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.repo.StockCrudOperation;

import java.util.List;

public class StockViewModel extends AndroidViewModel {

    private LiveData<List<Stock>> stockList;

    public StockViewModel(@NonNull Application application) {
        super(application);
        StockCrudOperation operation = new StockCrudOperation(application);
        stockList = ((StockDao) operation.getAsync().getDao()).getAll();

    }


    public LiveData<List<Stock>> getStockList() {
        return stockList;
    }

}
