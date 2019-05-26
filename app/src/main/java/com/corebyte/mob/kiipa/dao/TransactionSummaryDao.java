package com.corebyte.mob.kiipa.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.TransactionSummary;

import java.util.Date;
import java.util.List;

@Dao
public interface TransactionSummaryDao extends BaseDao<TransactionSummary> {

    @Override
    @Query("SELECT * FROM transaction_summary")
    List<TransactionSummary> getAll();

    @Override
    @Query("SELECT * FROM transaction_summary WHERE id = :id")
    TransactionSummary findById(long id);

    @Query("SELECT * FROM transaction_summary WHERE date(created_at) = date(:date)")
    List<TransactionSummary> findByDate(Date date);

    @Query("SELECT COUNT(*) FROM transaction_summary WHERE date(created_at) = date(:date)")
    int countTransactionByDate(Date date);
}
