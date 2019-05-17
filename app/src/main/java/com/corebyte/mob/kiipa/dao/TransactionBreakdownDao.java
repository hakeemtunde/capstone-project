package com.corebyte.mob.kiipa.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.TransactionBreakdown;

import java.util.List;

@Dao
public interface TransactionBreakdownDao extends BaseDao<TransactionBreakdown> {

    @Override
    @Query("SELECT * FROM transaction_breakdowns")
    List<TransactionBreakdown> getAll();

    @Override
    @Query("SELECT * FROM transaction_breakdowns WHERE id = :id")
    TransactionBreakdown findById(long id);

}
