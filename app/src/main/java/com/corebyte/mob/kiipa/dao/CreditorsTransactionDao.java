package com.corebyte.mob.kiipa.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.CreditorsTransaction;

import java.util.Date;
import java.util.List;

@Dao
public interface CreditorsTransactionDao extends BaseDao<CreditorsTransaction> {

    @Override
    @Query("SELECT * FROM creditors_transactions")
    List<CreditorsTransaction> getAll();

    @Override
    @Query("SELECT * FROM creditors_transactions WHERE id = :id")
    CreditorsTransaction findById(long id);

    @Query("SELECT * FROM creditors_transactions WHERE strftime('%d-%m-%Y', 'created_at') = strftime('%d-%m-%Y', :date)")
    List<CreditorsTransaction> findByDate(Date date);

    @Query("SELECT COUNT(*) FROM creditors_transactions WHERE strftime('%d-%m-%Y', 'created_at') = strftime('%d-%m-%Y', :date)")
    int countCreditorsTransactionByDate(Date date);

}
