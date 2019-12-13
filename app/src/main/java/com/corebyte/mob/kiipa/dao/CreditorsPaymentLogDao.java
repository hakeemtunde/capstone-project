package com.corebyte.mob.kiipa.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.CreditorsPaymentLog;
import com.corebyte.mob.kiipa.repo.AppQuery;

import java.util.Date;
import java.util.List;

@Dao
public interface CreditorsPaymentLogDao extends BaseDao<CreditorsPaymentLog> {

    @Override
    @Query("SELECT * FROM creditors_payment_log")
    LiveData<List<CreditorsPaymentLog>> getAll();

    @Override
    @Query("SELECT * FROM creditors_payment_log WHERE id = :id")
    CreditorsPaymentLog findById(long id);

    @Override
    @Query("SELECT * FROM creditors_payment_log")
    List<CreditorsPaymentLog> getAllRecords();

    @Query("SELECT * FROM creditors_payment_log WHERE strftime('%d-%m-%Y', 'created_at') = strftime('%d-%m-%Y', :date)")
    List<CreditorsPaymentLog> findByDate(Date date);

}
