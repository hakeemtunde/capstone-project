package com.corebyte.mob.kiipa.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.Stock;

import java.util.Date;
import java.util.List;

@Dao
public interface StockDao extends BaseDao<Stock> {

    @Override
    @Query("SELECT * FROM stocks")
    List<Stock> getAll();

    @Override
    @Query("SELECT * FROM stocks WHERE id = :id")
    Stock findById(long id);

    @Query("SELECT * FROM stocks WHERE date(expireDate) - date('now') <= :days")
    List<Stock> findExpireStockIn(int days);

    @Query("SELECT * FROM stocks WHERE expireDate BETWEEN date(:endDate) AND date('now')")
    List<Stock> findExpireStockIBetween(Date endDate);

    @Query("SELECT * FROM stocks WHERE date(expireDate) = date('now', '+'||:days||' days')")
    List<Stock> findExpireStockIn2(int days);
}
