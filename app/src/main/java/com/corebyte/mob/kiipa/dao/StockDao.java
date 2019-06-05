package com.corebyte.mob.kiipa.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.Stock;

import java.util.Date;
import java.util.List;

@Dao
public interface StockDao extends BaseDao<Stock> {

    @Override
    @Query("SELECT * FROM stocks")
    LiveData<List<Stock>> getAll();

    @Override
    @Query("SELECT * FROM stocks WHERE id = :id")
    Stock findById(long id);

    @Query("SELECT * FROM stocks WHERE date(expireDate) = date('now', '+'||:days||' days')")
    List<Stock> findExpireStockIn(int days);

    @Override
    @Query("SELECT * FROM stocks")
    List<Stock> getAllRecords();
}
