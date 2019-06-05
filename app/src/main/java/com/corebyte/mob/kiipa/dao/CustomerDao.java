package com.corebyte.mob.kiipa.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.Customer;

import java.util.List;

@Dao
public interface CustomerDao extends BaseDao<Customer> {

    @Override
    @Query("SELECT * FROM customers")
    LiveData<List<Customer>> getAll();

    @Override
    @Query("SELECT * FROM customers WHERE id = :id")
    Customer findById(long id);

    @Override
    @Query("SELECT * FROM customers")
    List<Customer> getAllRecords();
}
